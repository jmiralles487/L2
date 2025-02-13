package pe.gob.mtpe.rios.aplicacion.interfaces.SRISLOSSYS;

import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadLstRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios.UsuarioLstRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios.UsuarioSistemaCredencialesRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios.UsuarioSistemaValidacionRequest;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalReg;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalReg;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaValidacion;

import java.util.List;

public interface IUsuarioSistemaServicio {
    ResponseData<UsuarioSistemaValidacion> validarUsuarioSistema(UsuarioSistemaValidacionRequest usuarioSistemaValidacionRequest) throws Exception;

    ResponseData<UsuarioSistemaDatos> datosUsuarioSistema(UsuarioSistemaCredencialesRequest usuarioSistemaCredencialesRequest) throws Exception;

    ResponseData<UsuarioSistemaTotalReg> totalRegistroUsuario(UsuarioLstRegistroRequest usuarioLstRegistroRequest) throws Exception;
    
    ResponseData<List<UsuarioSistemaTotalDatos>> totalDatosRegUsuario(UsuarioLstRegistroRequest usuarioLstRegistroRequest) throws Exception;
}
