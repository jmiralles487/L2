package pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios;

import lombok.Data;

@Data
public class UsuarioSistemaCredencialesRequest {
    private String username;
    private String password;
    private String recaptchaToken;
}
