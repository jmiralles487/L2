package pe.gob.mtpe.rios.aplicacion.interfaces.Externo;

import pe.gob.mtpe.rios.aplicacion.dtos.Request.Externo.SunatRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.Externos.SunatEmpresa;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;

import java.util.concurrent.CompletableFuture;

public interface ISunatServicio {
    CompletableFuture<ResponseData<SunatEmpresa>> obtenerSunatAsync (SunatRequest sunatRequest);
}
