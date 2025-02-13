package pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSistemaTotalDatos {
    private Integer id;
    private String usuario;
    private String nombres;
    private String telefono;
    private String cargo;
    private String rucEntidad;
    private String entidad;
    private String rol;
    private String estado;
}
