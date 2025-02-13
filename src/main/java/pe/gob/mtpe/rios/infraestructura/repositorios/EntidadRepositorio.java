package pe.gob.mtpe.rios.infraestructura.repositorios;

import javafx.util.Pair;
import pe.gob.mtpe.rios.dominio.agregados.StoreProcedureResponse;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadExisteRegistro;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalReg;
import pe.gob.mtpe.rios.dominio.interfaces.IEntidadRepositorio;
import pe.gob.mtpe.rios.infraestructura.MapeosEntidades.MapReaderToEntidad;
import pe.gob.mtpe.rios.infraestructura.configuraciones.DatabaseProcedureExecutor;
import pe.gob.mtpe.rios.infraestructura.configuraciones.StoreProcedureName;
import pe.gob.mtpe.rios.infraestructura.contexto.SrisLossSysContext;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EntidadRepositorio implements IEntidadRepositorio {

    private final DatabaseProcedureExecutor procedureExecutor;
    private final MapReaderToEntidad mapReaderToEntidad;

    public EntidadRepositorio(DatabaseProcedureExecutor procedureExecutor) {
        this.procedureExecutor = procedureExecutor;
        this.mapReaderToEntidad = new MapReaderToEntidad();
    }

    @Override
    public CompletableFuture<Pair<List<EntidadTotalReg>, StoreProcedureResponse>> entidadTotalRegistroAsync(String jsonInput) {

        StoreProcedureName storedProcedure = new StoreProcedureName("SRISLOSSYS", "PKG_RIOSENTIDADSISTEMA", "SPR_TOTALREG_ENTIDADSISTEMA");
        String procedureCall = storedProcedure.getProcedureCall();

        return procedureExecutor.executeProcedure(
                procedureCall,
                jsonInput,
                resultSet -> {
                    try {
                        // Reutilizar el método mapValidacionUsuario
                        return mapReaderToEntidad.mapTotalRegistro(resultSet);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al mapear el ResultSet a UsuarioSistemaValidacion", e);
                    }
                }
        );
    }

    @Override
    public CompletableFuture<Pair<List<EntidadTotalDatos>, StoreProcedureResponse>> entidadTotalDatosAsync(String jsonInput) {
        StoreProcedureName storedProcedure = new StoreProcedureName("SRISLOSSYS", "PKG_RIOSENTIDADSISTEMA", "SPR_TOTALDATOS_ENTIDADSISTEMA");
        String procedureCall = storedProcedure.getProcedureCall();

        return procedureExecutor.executeProcedure(
                procedureCall,
                jsonInput,
                resultSet -> {
                    try {
                        // Reutilizar el método mapValidacionUsuario
                        return mapReaderToEntidad.mapTotalDatos(resultSet);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al mapear el ResultSet a UsuarioSistemaValidacion", e);
                    }
                }
        );
    }

    @Override
    public CompletableFuture<Pair<List<EntidadExisteRegistro>, StoreProcedureResponse>> entidadExisteRegistroAsync(String jsonInput) {
        StoreProcedureName storedProcedure = new StoreProcedureName("SRISLOSSYS", "PKG_RIOSENTIDADSISTEMA", "SPR_VALIDACION_ENTIDADSISTEMA");
        String procedureCall = storedProcedure.getProcedureCall();

        return procedureExecutor.executeProcedure(
                procedureCall,
                jsonInput,
                resultSet -> {
                    try {
                        // Reutilizar el método mapValidacionUsuario
                        return mapReaderToEntidad.mapExisteRegistro(resultSet);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al mapear el ResultSet a UsuarioSistemaValidacion", e);
                    }
                }
        );
    }

    @Override
    public CompletableFuture<Pair<List<EntidadDatos>, StoreProcedureResponse>> entidadDatosRegistroAsync(String jsonInput) {
        StoreProcedureName storedProcedure = new StoreProcedureName("SRISLOSSYS", "PKG_RIOSENTIDADSISTEMA", "SPR_DATOS_ENTIDADSISTEMA");
        String procedureCall = storedProcedure.getProcedureCall();

        return procedureExecutor.executeProcedure(
                procedureCall,
                jsonInput,
                resultSet -> {
                    try {
                        // Reutilizar el método mapValidacionUsuario
                        return mapReaderToEntidad.mapDatos(resultSet);
                    } catch (Exception e) {
                        throw new RuntimeException("Error al mapear el ResultSet a UsuarioSistemaValidacion", e);
                    }
                }
        );
    }
}
