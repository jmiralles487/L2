package pe.gob.mtpe.rios.aplicacion.servicios.Externo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.Externo.SunatRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.Externos.SunatEmpresa;
import pe.gob.mtpe.rios.aplicacion.interfaces.Externo.ISunatServicio;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;

import java.util.concurrent.CompletableFuture;

@Service
public class SunatServicio implements ISunatServicio {

    @Value("${sunat.url}")
    private String sunatUrl;

    private final WebClient.Builder webClientBuilder;

    private WebClient webClient;

    @Autowired
    public SunatServicio(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder.baseUrl(sunatUrl).build();
    }


    @Override
    public CompletableFuture<ResponseData<SunatEmpresa>> obtenerSunatAsync(SunatRequest sunatRequest) {
        try {
            String url = "{id}";

            SunatEmpresa response = webClient.get()
                    .uri(url, sunatRequest.getNroRuc().trim())
                    .retrieve()
                    .bodyToMono(SunatEmpresa.class)
                    .block();

            return CompletableFuture.completedFuture(new ResponseData<>(response, "Consulta Exitosa"));
        } catch (Exception ex) {
            return CompletableFuture.completedFuture(new ResponseData<>(null, "Error: " + ex.getMessage()));
        }
    }
}
