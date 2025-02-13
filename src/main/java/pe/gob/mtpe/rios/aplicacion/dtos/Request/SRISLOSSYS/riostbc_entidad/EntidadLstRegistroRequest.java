package pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad;

import lombok.Data;

@Data
public class EntidadLstRegistroRequest {
    private Integer LIMIT;
    private Integer OFFSET;
    private String BUSQUEDA;
}
