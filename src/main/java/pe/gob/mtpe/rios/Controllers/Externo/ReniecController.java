package pe.gob.mtpe.rios.Controllers.Externo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.mtpe.rios.aplicacion.dtos.Request.Externo.ReniecRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.Externos.ReniecResponse;
import pe.gob.mtpe.rios.aplicacion.interfaces.Externo.IReniecServicio;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/reniec")
public class ReniecController {

    private final IReniecServicio reniecServicio;

    @Autowired
    public ReniecController(IReniecServicio reniecServicio) {
        this.reniecServicio = reniecServicio;
    }

    @PostMapping("/obtener-reniec-async")
    public CompletableFuture<ResponseData<ReniecResponse>> obtenerReniecAsync(@RequestBody ReniecRequest reniecRequest) {
        return reniecServicio.obtenerReniecAsync(reniecRequest);
    }
}
