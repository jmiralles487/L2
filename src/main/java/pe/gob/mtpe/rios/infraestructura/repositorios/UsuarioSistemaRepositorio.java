package pe.gob.mtpe.rios.infraestructura.repositorios;

import javafx.util.Pair;
import pe.gob.mtpe.rios.dominio.agregados.StoreProcedureResponse;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalReg;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaValidacion;
import pe.gob.mtpe.rios.dominio.interfaces.IUsuarioSistemaRepositorio;
import pe.gob.mtpe.rios.infraestructura.MapeosEntidades.MapReaderToUsuarioSistema;
import pe.gob.mtpe.rios.infraestructura.configuraciones.DatabaseProcedureExecutor;
import pe.gob.mtpe.rios.infraestructura.contexto.SrisLossSysContext;
import pe.gob.mtpe.rios.infraestructura.configuraciones.StoreProcedureName;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UsuarioSistemaRepositorio implements IUsuarioSistemaRepositorio {

    private final MapReaderToUsuarioSistema mapUsuarioSistema;
    private final DatabaseProcedureExecutor procedureExecutor;

    public UsuarioSistemaRepositorio(DatabaseProcedureExecutor procedureExecutor) {
        this.mapUsuarioSistema = new MapReaderToUsuarioSistema();
        this.procedureExecutor = procedureExecutor;
    }

    @Override
    public CompletableFuture<Pair<List<UsuarioSistemaValidacion>, StoreProcedureResponse>> usrSistemaValidacionAsync(String jsonInput) {
//        return CompletableFuture.supplyAsync(() -> {
//            List<UsuarioSistemaValidacion> lstUsuariValidacion = new ArrayList<>();
//            StoreProcedureResponse storeProcResponse = new StoreProcedureResponse();
//            Connection connection = null;
//
//            try {
//                connection = context.dataSource().getConnection();
//                // Configuración del CallableStatement
//                CallableStatement callableStatement = connection.prepareCall(
//                        "BEGIN SRISLOSSYS.PKG_RIOSUSUARIOSISTEMA.SPR_VALIDAR_USUARIOSISTEMA(?, ?); END;"
//                );
//
//                // Parámetro de entrada (CLOB)
//                Clob clob = connection.createClob();
//                clob.setString(1, jsonInput); // Asignar JSON al CLOB
//                callableStatement.setClob(1, clob);
//
//                // Parámetro de salida (SYS_REFCURSOR)
//                callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
//
//                // Ejecutar el procedimiento
//                callableStatement.execute();
//
//                // Obtener el cursor de salida
//                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
//
//                // Mapear los resultados del cursor
//                while (resultSet.next()) {
//                    UsuarioSistemaValidacion usuarioSistemaDatos = mapUsuarioSistema.mapValidacionUsuario(resultSet);
//                    lstUsuariValidacion.add(usuarioSistemaDatos);
//                }
//
//                storeProcResponse = new StoreProcedureResponse(200, "Consulta Exitosa");
//            } catch (SQLException e) {
//                storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.ERROR_CONEXION_BD);
//            } catch (NullPointerException e) {
//                storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.COLUMNA_NO_ENCONTRADA);
//            } catch (NumberFormatException e) {
//                storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.ERROR_CONVERSION_TIPO_DATOS);
//            } catch (Exception e) {
//                storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.ERROR_INESPERADO);
//            } finally {
//                if (connection != null) {
//                    try {
//                        connection.close();
//                    } catch (SQLException e) {
//                        storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.ERROR_CONEXION_BD);
//                    }
//                }
//            }
//            return new Pair<>(lstUsuariValidacion, storeProcResponse);
//        });

        StoreProcedureName storedProcedure = new StoreProcedureName("SRISLOSSYS", "PKG_RIOSUSUARIOSISTEMA", "SPR_VALIDAR_USUARIOSISTEMA");
        String procedureCall = storedProcedure.getProcedureCall();

        return procedureExecutor.executeProcedure(
                procedureCall,
                jsonInput,
                resultSet -> {
                    try {
                        // Reutilizar el método mapValidacionUsuario
                        return mapUsuarioSistema.mapValidacionUsuario(resultSet);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al mapear el ResultSet a UsuarioSistemaValidacion", e);
                    }
                }
        );
    }

    @Override
    public CompletableFuture<Pair<List<UsuarioSistemaDatos>, StoreProcedureResponse>> usrSistemaDatosAsync(String jsonInput) {
//        return CompletableFuture.supplyAsync(() -> {
//            List<UsuarioSistemaDatos> lstUsuariValidacion = new ArrayList<>();
//            StoreProcedureResponse storeProcResponse = new StoreProcedureResponse();
//            Connection connection = null;
//
//            try {
//                connection = context.dataSource().getConnection();
//                // Configuración del CallableStatement
//                CallableStatement callableStatement = connection.prepareCall(
//                        "BEGIN SRISLOSSYS.PKG_RIOSUSUARIOSISTEMA.SPR_DATOS_USUARIOSISTEMA(?, ?); END;"
//                );
//
//                // Parámetro de entrada (CLOB)
//                Clob clob = connection.createClob();
//                clob.setString(1, jsonInput); // Asignar JSON al CLOB
//                callableStatement.setClob(1, clob);
//
//                // Parámetro de salida (SYS_REFCURSOR)
//                callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
//
//                // Ejecutar el procedimiento
//                callableStatement.execute();
//
//                // Obtener el cursor de salida
//                ResultSet resultSet = (ResultSet) callableStatement.getObject(2);
//
//                // Mapear los resultados del cursor
//                while (resultSet.next()) {
//                    UsuarioSistemaDatos usuarioSistemaDatos = mapUsuarioSistema.mapDatosUsuario(resultSet);
//                    lstUsuariValidacion.add(usuarioSistemaDatos);
//                }
//                storeProcResponse = new StoreProcedureResponse(200, "Consulta Exitosa");
//            } catch (SQLException e) {
//                storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.ERROR_CONEXION_BD);
//            } catch (NullPointerException e) {
//                storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.COLUMNA_NO_ENCONTRADA);
//            } catch (NumberFormatException e) {
//                storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.ERROR_CONVERSION_TIPO_DATOS);
//            } catch (Exception e) {
//                storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.ERROR_INESPERADO);
//            } finally {
//                if (connection != null) {
//                    try {
//                        connection.close();
//                    } catch (SQLException e) {
//                        storeProcResponse = ErrorHelper.handleError(e, ErrorCodes.ERROR_CONEXION_BD);
//                    }
//                }
//            }
//            return new Pair<>(lstUsuariValidacion, storeProcResponse);
//        });

        StoreProcedureName storedProcedure = new StoreProcedureName("SRISLOSSYS", "PKG_RIOSUSUARIOSISTEMA", "SPR_DATOS_USUARIOSISTEMA");
        String procedureCall = storedProcedure.getProcedureCall();

        return procedureExecutor.executeProcedure(
                procedureCall,
                jsonInput,
                resultSet -> {
                    try {
                        return mapUsuarioSistema.mapDatosUsuario(resultSet);
                    }
                    catch (Exception e) {
                        throw new RuntimeException("Error al mapear el ResultSet a UsuarioSistemaValidacion", e);
                    }
                }
         );
    }

    @Override
    public CompletableFuture<Pair<List<UsuarioSistemaTotalReg>, StoreProcedureResponse>> usrTotalRegistroAsync(String jsonInput) {
        StoreProcedureName storedProcedure = new StoreProcedureName("SRISLOSSYS", "PKG_RIOSUSUARIOSISTEMA", "SPR_TOTALREG_USUARIOSISTEMA");
        String procedureCall = storedProcedure.getProcedureCall();

        return procedureExecutor.executeProcedure(
                procedureCall,
                jsonInput,
                resultSet -> {
                    try {
                        return mapUsuarioSistema.mapTotalRegistro(resultSet);
                    }
                    catch (Exception e) {
                        throw new RuntimeException("Error al mapear el Resultado", e);
                    }
                }
        );
    }

    @Override
    public CompletableFuture<Pair<List<UsuarioSistemaTotalDatos>, StoreProcedureResponse>> usrTotalDatosAsync(String jsonInput) {
        StoreProcedureName storedProcedure = new StoreProcedureName("SRISLOSSYS", "PKG_RIOSUSUARIOSISTEMA", "SPR_TOTALDATOS_USUARIOSISTEMA");
        String procedureCall = storedProcedure.getProcedureCall();

        return procedureExecutor.executeProcedure(
                procedureCall,
                jsonInput,
                resultSet -> {
                    try {
                        return mapUsuarioSistema.mapTotalDatos(resultSet);
                    }
                    catch (Exception e) {
                        throw new RuntimeException("Error al mapear el Resultado", e);
                    }
                }
        );
    }
}
