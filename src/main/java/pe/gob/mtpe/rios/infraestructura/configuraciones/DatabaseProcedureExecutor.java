package pe.gob.mtpe.rios.infraestructura.configuraciones;

import pe.gob.mtpe.rios.dominio.agregados.StoreProcedureResponse;
import pe.gob.mtpe.rios.infraestructura.contexto.SrisLossSysContext;
import javafx.util.Pair;
import oracle.jdbc.OracleTypes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DatabaseProcedureExecutor {

    private final SrisLossSysContext context;

    public DatabaseProcedureExecutor(SrisLossSysContext context) {
        this.context = context;
    }

    public <T> CompletableFuture<Pair<List<T>, StoreProcedureResponse>> executeProcedure(
            String procedureCall,
            String inputJson,
            ResultSetMapper<T> mapper
    ) {
        return CompletableFuture.supplyAsync(() -> {
            List<T> resultList = new ArrayList<>();
            StoreProcedureResponse response;
            Connection connection = null;

            try {
                connection = context.dataSource().getConnection();
                CallableStatement callableStatement = connection.prepareCall(procedureCall);

                // Configuración de parámetros
                Clob clob = connection.createClob();
                clob.setString(1, inputJson);
                callableStatement.setClob(1, clob);

                callableStatement.registerOutParameter(2, OracleTypes.CURSOR);

                // Ejecutar procedimiento almacenado
                callableStatement.execute();

                // Mapear resultados
                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
                while (resultSet.next()) {
                    T mappedResult = mapper.map(resultSet);
                    resultList.add(mappedResult);
                }

                response = new StoreProcedureResponse(200, "Consulta Exitosa");
            } catch (SQLException e) {
                response = ErrorHelper.handleError(e, ErrorCodes.ERROR_CONEXION_BD);
            } catch (NullPointerException e) {
                response = ErrorHelper.handleError(e, ErrorCodes.COLUMNA_NO_ENCONTRADA);
            } catch (NumberFormatException e) {
                response = ErrorHelper.handleError(e, ErrorCodes.ERROR_CONVERSION_TIPO_DATOS);
            } catch (Exception e) {
                response = ErrorHelper.handleError(e, ErrorCodes.ERROR_INESPERADO);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        response = ErrorHelper.handleError(e, ErrorCodes.ERROR_CONEXION_BD);
                    }
                }
            }
            return new Pair<>(resultList, response);
        });
    }

    // Interfaz para mapear ResultSet
    public interface ResultSetMapper<T> {
        T map(ResultSet resultSet) throws SQLException;
    }

}
