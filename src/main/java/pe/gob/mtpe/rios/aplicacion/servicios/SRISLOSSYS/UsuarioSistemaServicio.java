package pe.gob.mtpe.rios.aplicacion.servicios.SRISLOSSYS;

import org.springframework.stereotype.Service;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadLstRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios.UsuarioLstRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios.UsuarioSistemaCredencialesRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios.UsuarioSistemaValidacionRequest;
import pe.gob.mtpe.rios.aplicacion.interfaces.SRISLOSSYS.IUsuarioSistemaServicio;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;
import pe.gob.mtpe.rios.dominio.agregados.StoreProcedureResponse;
import pe.gob.mtpe.rios.dominio.agregados.XmlStoreProcedureRequest;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalReg;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaValidacion;
import pe.gob.mtpe.rios.dominio.interfaces.IUsuarioSistemaRepositorio;
import pe.gob.mtpe.rios.infraestructura.configuraciones.DatabaseProcedureExecutor;
import pe.gob.mtpe.rios.infraestructura.contexto.SrisLossSysContext;
import pe.gob.mtpe.rios.infraestructura.repositorios.UsuarioSistemaRepositorio;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioSistemaServicio implements IUsuarioSistemaServicio {

    private final IUsuarioSistemaRepositorio usuarioSistemaRepositorio = new UsuarioSistemaRepositorio(new DatabaseProcedureExecutor(new SrisLossSysContext()));

    @Override
    public ResponseData<UsuarioSistemaValidacion> validarUsuarioSistema(UsuarioSistemaValidacionRequest usuarioSistemaValidacionRequest) throws Exception {
        List<UsuarioSistemaValidacionRequest> lstUsuarioSistemaValidacionRequest = new ArrayList<>();
        lstUsuarioSistemaValidacionRequest.add(usuarioSistemaValidacionRequest);
        String sxml = XmlStoreProcedureRequest.generarXml(lstUsuarioSistemaValidacionRequest);
        return usuarioSistemaRepositorio.usrSistemaValidacionAsync(sxml)
                .thenApply(tarea -> {
                    List<UsuarioSistemaValidacion> datos = (List<UsuarioSistemaValidacion>) tarea.getKey();
                    StoreProcedureResponse respuesta = (StoreProcedureResponse) tarea.getValue();
                    return respuesta.getCodigo() == 200
                            ? new ResponseData<UsuarioSistemaValidacion>(datos.isEmpty() ? null : datos.getFirst(), respuesta.getMensaje(), respuesta.getCodigo())
                            : new ResponseData<UsuarioSistemaValidacion>(null, respuesta.getMensaje(), respuesta.getCodigo());
                }).join();
    }

    @Override
    public ResponseData<UsuarioSistemaDatos> datosUsuarioSistema(UsuarioSistemaCredencialesRequest usuarioSistemaCredencialesRequest) throws Exception {
        List<UsuarioSistemaCredencialesRequest> lstUsuarioSistemaCredencialesRequest = new ArrayList<>();
        lstUsuarioSistemaCredencialesRequest.add(usuarioSistemaCredencialesRequest);
        String sxml = XmlStoreProcedureRequest.generarXml(lstUsuarioSistemaCredencialesRequest);
        return usuarioSistemaRepositorio.usrSistemaDatosAsync(sxml)
                .thenApply(tarea -> {
                    List<UsuarioSistemaDatos> datos = (List<UsuarioSistemaDatos>) tarea.getKey();
                    StoreProcedureResponse respuesta = (StoreProcedureResponse) tarea.getValue();
                    return respuesta.getCodigo() == 200
                            ? new ResponseData<UsuarioSistemaDatos>(datos.isEmpty() ? null : datos.getFirst(), respuesta.getMensaje(), respuesta.getCodigo())
                            : new ResponseData<UsuarioSistemaDatos>(null, respuesta.getMensaje(), respuesta.getCodigo());
                }).join();
    }

    @Override
    public ResponseData<UsuarioSistemaTotalReg> totalRegistroUsuario(UsuarioLstRegistroRequest usuarioLstRegistroRequest) throws Exception {
        List<UsuarioLstRegistroRequest> usuarioLstRegistroRequestArrayList = new ArrayList<>();
        usuarioLstRegistroRequestArrayList.add(usuarioLstRegistroRequest);
        String sxml = XmlStoreProcedureRequest.generarXml(usuarioLstRegistroRequestArrayList);
        return usuarioSistemaRepositorio.usrTotalRegistroAsync(sxml)
                .thenApply(tarea -> {
                    List<UsuarioSistemaTotalReg> datos = (List<UsuarioSistemaTotalReg>) tarea.getKey();
                    StoreProcedureResponse respuesta = (StoreProcedureResponse) tarea.getValue();
                    return respuesta.getCodigo() == 200
                            ? new ResponseData<UsuarioSistemaTotalReg>(datos.isEmpty() ? null : datos.getFirst(), respuesta.getMensaje(), respuesta.getCodigo())
                            : new ResponseData<UsuarioSistemaTotalReg>(null, respuesta.getMensaje(), respuesta.getCodigo());
                }).join();
    }

    @Override
    public ResponseData<List<UsuarioSistemaTotalDatos>> totalDatosRegUsuario(UsuarioLstRegistroRequest usuarioLstRegistroRequest) throws Exception {
        List<UsuarioLstRegistroRequest> usuarioLstRegistroRequestArrayList = new ArrayList<>();
        usuarioLstRegistroRequestArrayList.add(usuarioLstRegistroRequest);
        String sxml = XmlStoreProcedureRequest.generarXml(usuarioLstRegistroRequestArrayList);
        return usuarioSistemaRepositorio.usrTotalDatosAsync(sxml)
                .thenApply(tarea -> {
                    List<UsuarioSistemaTotalDatos> datos = tarea.getKey();
                    StoreProcedureResponse respuesta = tarea.getValue();
                    return respuesta.getCodigo() == 200
                            ? new ResponseData<>(datos.isEmpty() ? null : datos, respuesta.getMensaje(), respuesta.getCodigo())
                            : new ResponseData<List<UsuarioSistemaTotalDatos>>(null, respuesta.getMensaje(), respuesta.getCodigo());
                }).join();
    }
}
