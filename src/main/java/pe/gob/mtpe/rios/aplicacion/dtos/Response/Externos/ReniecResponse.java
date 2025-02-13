package pe.gob.mtpe.rios.aplicacion.dtos.Response.Externos;

import lombok.Data;

@Data
public class ReniecResponse {
    private String codigo;
    private String mensaje;
    private String dniConsultado;
    private String cabecera;
    private ReniecDatosPersonales personaBean;
}