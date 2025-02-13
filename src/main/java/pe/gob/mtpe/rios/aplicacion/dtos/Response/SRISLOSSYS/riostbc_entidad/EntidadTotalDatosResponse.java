package pe.gob.mtpe.rios.aplicacion.dtos.Response.SRISLOSSYS.riostbc_entidad;

import lombok.Data;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;

import java.util.List;

@Data
public class EntidadTotalDatosResponse {
    private List<EntidadTotalDatos> results;
    private int count;
}
