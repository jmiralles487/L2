package pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSistemaValidacion {
    @Nullable
    private Integer NumUsuariosistema;
    @Nullable
    private String Codusu;
    @Nullable
    private String Codpersonal;
    @Nullable
    private Integer existeRegistro;
    @Nullable
    private String estadoUsuarioActivo;
}
