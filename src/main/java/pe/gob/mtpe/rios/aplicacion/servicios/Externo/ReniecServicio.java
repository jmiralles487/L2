package pe.gob.mtpe.rios.aplicacion.servicios.Externo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.Externo.ReniecRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.Externos.ReniecResponse;
import pe.gob.mtpe.rios.aplicacion.interfaces.Externo.IReniecServicio;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;

import java.util.concurrent.CompletableFuture;

@Service
public class ReniecServicio implements IReniecServicio {

    @Value("${reniec.url}")
    private String reniecUrl;

    private final WebClient.Builder webClientBuilder;

    private WebClient webClient;

    @Autowired
    public ReniecServicio(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder.baseUrl(reniecUrl).build();
    }

    public CompletableFuture<ResponseData<ReniecResponse>> obtenerReniecAsync(ReniecRequest reniecRequest) {
        try {
            String url = "/reniecld-rest/consulta/datos/{dni}/{id}";

            ReniecResponse response = webClient.get()
                    .uri(url, reniecRequest.getNroDNI().trim(), "119")
                    .retrieve()
                    .bodyToMono(ReniecResponse.class)
                    .block();

            return CompletableFuture.completedFuture(new ResponseData<>(response, "Consulta Exitosa"));
        } catch (Exception ex) {
            return CompletableFuture.completedFuture(new ResponseData<>(null, "Error: " + ex.getMessage()));
        }
    }
}