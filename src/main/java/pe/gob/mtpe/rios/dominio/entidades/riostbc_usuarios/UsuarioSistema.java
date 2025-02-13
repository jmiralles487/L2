package pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSistema {
    @Nullable
    private UsuarioSistemaValidacion validacion;
    @Nullable
    private UsuarioSistemaDatos datos;
}