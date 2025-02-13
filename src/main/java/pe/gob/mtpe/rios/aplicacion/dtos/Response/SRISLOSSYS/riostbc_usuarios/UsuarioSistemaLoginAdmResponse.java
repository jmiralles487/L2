package pe.gob.mtpe.rios.aplicacion.dtos.Response.SRISLOSSYS.riostbc_usuarios;

import lombok.Data;

@Data
public class UsuarioSistemaLoginAdmResponse {
    private Integer numUsuarioSistema;
    private Integer numPerfil;
    private String codPersonal;
    private String token;
    private String codPerfil;
    private String nombresCompleto;
    private String correo;
    private String telefono;
    private String cargo;
    private String tipoDocId;
}