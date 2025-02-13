package pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios;

import lombok.Data;

@Data
public class UsuarioLstRegistroRequest {
    private Integer LIMIT;
    private Integer OFFSET;
    private String BUSQUEDA;
}
