package pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntidadDatos {
    private int numEntidad;
    private String nombre;
    private String domicilio;
    private String codEntidad;
    private String rucEntidad;
    private String correo;
    private int telefono;
    private String activo;
}
