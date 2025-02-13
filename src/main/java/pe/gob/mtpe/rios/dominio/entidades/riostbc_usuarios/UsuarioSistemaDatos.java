package pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSistemaDatos {
    @Nullable
    private String codpersonal;
    @Nullable
    private String password;
    @Nullable
    private String estado;
    @Nullable
    private String nombreCompleto;
    @Nullable
    private Integer numperfil;
    @Nullable
    private String codperfil;
    @Nullable
    private String correo;
    @Nullable
    private String telefono;
    @Nullable
    private String cargo;
    @Nullable
    private String tipodocid;
    @Nullable
    private String nombres;
    @Nullable
    private String apepaterno;
    @Nullable
    private String apematerno;
}