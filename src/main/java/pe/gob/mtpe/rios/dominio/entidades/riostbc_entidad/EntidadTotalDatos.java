package pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadTotalDatos {
    private Integer id;
    private String ruc;
    private String razonSocial;
    private String domicilio;
    private String codigoUnidad;
    private String correo;
    private String telefono;
    private String activo;
}
