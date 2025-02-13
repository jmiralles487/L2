package pe.gob.mtpe.rios.Controllers.Externo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.Externo.ReniecRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.Externo.SunatRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.Externos.ReniecResponse;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.Externos.SunatEmpresa;
import pe.gob.mtpe.rios.aplicacion.interfaces.Externo.ISunatServicio;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/sunat")
public class SunatController {
    private final ISunatServicio sunatServicio;

    @Autowired
    public SunatController(ISunatServicio sunatServicio) {
        this.sunatServicio = sunatServicio;
    }

    @PostMapping("/obtener-sunat-async")
    public CompletableFuture<ResponseData<SunatEmpresa>> obtenerReniecAsync(@RequestBody SunatRequest sunatRequest) {
        return sunatServicio.obtenerSunatAsync(sunatRequest);
    }
}
