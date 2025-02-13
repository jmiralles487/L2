package pe.gob.mtpe.rios.aplicacion.dtos.Response.SRISLOSSYS.riostbc_usuarios;

import lombok.Data;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalDatos;

import java.util.List;

@Data
public class UsuarioTotalDatosResponse {
    private List<UsuarioSistemaTotalDatos> results;
    private int count;
}
