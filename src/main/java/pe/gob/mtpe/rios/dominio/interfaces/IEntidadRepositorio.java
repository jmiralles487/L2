package pe.gob.mtpe.rios.dominio.interfaces;

import javafx.util.Pair;
import pe.gob.mtpe.rios.dominio.agregados.StoreProcedureResponse;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadExisteRegistro;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalReg;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IEntidadRepositorio {
    CompletableFuture<Pair<List<EntidadTotalReg>, StoreProcedureResponse>> entidadTotalRegistroAsync(String jsonInput);

    CompletableFuture<Pair<List<EntidadTotalDatos>, StoreProcedureResponse>> entidadTotalDatosAsync(String jsonInput);

    CompletableFuture<Pair<List<EntidadExisteRegistro>, StoreProcedureResponse>> entidadExisteRegistroAsync(String jsonInput);

    CompletableFuture<Pair<List<EntidadDatos>, StoreProcedureResponse>> entidadDatosRegistroAsync(String jsonInput);
}
