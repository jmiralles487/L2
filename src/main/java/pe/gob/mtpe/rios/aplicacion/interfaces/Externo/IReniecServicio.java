package pe.gob.mtpe.rios.aplicacion.interfaces.Externo;

import pe.gob.mtpe.rios.aplicacion.dtos.Request.Externo.ReniecRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.Externos.ReniecResponse;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;

import java.util.concurrent.CompletableFuture;

public interface IReniecServicio {
    CompletableFuture<ResponseData<ReniecResponse>> obtenerReniecAsync(ReniecRequest reniecRequest);
}
