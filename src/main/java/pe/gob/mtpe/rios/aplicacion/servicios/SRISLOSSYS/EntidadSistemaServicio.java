package pe.gob.mtpe.rios.aplicacion.servicios.SRISLOSSYS;

import org.springframework.stereotype.Service;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadDatosRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadLstRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadRucRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.interfaces.SRISLOSSYS.IEntidadSistemaServicio;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;
import pe.gob.mtpe.rios.dominio.agregados.StoreProcedureResponse;
import pe.gob.mtpe.rios.dominio.agregados.XmlStoreProcedureRequest;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadExisteRegistro;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalReg;
import pe.gob.mtpe.rios.dominio.interfaces.IEntidadRepositorio;
import pe.gob.mtpe.rios.infraestructura.configuraciones.DatabaseProcedureExecutor;
import pe.gob.mtpe.rios.infraestructura.contexto.SrisLossSysContext;
import pe.gob.mtpe.rios.infraestructura.repositorios.EntidadRepositorio;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntidadSistemaServicio implements IEntidadSistemaServicio {

    private final IEntidadRepositorio entidadSistemaRepositorio = new EntidadRepositorio(new DatabaseProcedureExecutor(new SrisLossSysContext()));

    @Override
    public ResponseData<EntidadTotalReg> totalRegistroEntidad(EntidadLstRegistroRequest entidadLstRegistroRequest) throws Exception {
        List<EntidadLstRegistroRequest> lstentidadLstRegistroRequest = new ArrayList<>();
        lstentidadLstRegistroRequest.add(entidadLstRegistroRequest);
        String sxml = XmlStoreProcedureRequest.generarXml(lstentidadLstRegistroRequest);
        return entidadSistemaRepositorio.entidadTotalRegistroAsync(sxml)
                .thenApply(tarea -> {
                    List<EntidadTotalReg> datos = tarea.getKey();
                    StoreProcedureResponse respuesta = tarea.getValue();
                    return respuesta.getCodigo() == 200
                            ? new ResponseData<>(datos.isEmpty() ? null : datos.getFirst(), respuesta.getMensaje(), respuesta.getCodigo())
                            : new ResponseData<EntidadTotalReg>(null, respuesta.getMensaje(), respuesta.getCodigo());
                }).join();
    }

    @Override
    public ResponseData<List<EntidadTotalDatos>> totalDatosRegEntidad(EntidadLstRegistroRequest entidadLstRegistroRequest) throws Exception {
        List<EntidadLstRegistroRequest> lstentidadLstRegistroRequest = new ArrayList<>();
        lstentidadLstRegistroRequest.add(entidadLstRegistroRequest);
        String sxml = XmlStoreProcedureRequest.generarXml(lstentidadLstRegistroRequest);
        return entidadSistemaRepositorio.entidadTotalDatosAsync(sxml)
                .thenApply(tarea -> {
                    List<EntidadTotalDatos> datos = tarea.getKey();
                    StoreProcedureResponse respuesta = tarea.getValue();
                    return respuesta.getCodigo() == 200
                            ? new ResponseData<>(datos.isEmpty() ? null : datos, respuesta.getMensaje(), respuesta.getCodigo())
                            : new ResponseData<List<EntidadTotalDatos>>(null, respuesta.getMensaje(), respuesta.getCodigo());
                }).join();
    }

    @Override
    public ResponseData<EntidadExisteRegistro> existeRegistroEntidad(EntidadRucRegistroRequest entidadRucRegistroRequest) throws Exception {
        List<EntidadRucRegistroRequest> entidadRucRegistroRequestArrayList = new ArrayList<>();
        entidadRucRegistroRequestArrayList.add(entidadRucRegistroRequest);
        String sxml = XmlStoreProcedureRequest.generarXml(entidadRucRegistroRequestArrayList);
        return entidadSistemaRepositorio.entidadExisteRegistroAsync(sxml)
                .thenApply(tarea -> {
                    List<EntidadExisteRegistro> datos = tarea.getKey();
                    StoreProcedureResponse respuesta = tarea.getValue();
                    return respuesta.getCodigo() == 200
                            ? new ResponseData<>(datos.isEmpty() ? null : datos.getFirst(), respuesta.getMensaje(), respuesta.getCodigo())
                            : new ResponseData<EntidadExisteRegistro>(null, respuesta.getMensaje(), respuesta.getCodigo());
                }).join();
    }

    @Override
    public ResponseData<EntidadDatos> datosRegistroEntidad(EntidadDatosRequest entidadDatosRequest) throws Exception {
        List<EntidadDatosRequest> entidadDatosRequestArrayList = new ArrayList<>();
        entidadDatosRequestArrayList.add(entidadDatosRequest);
        String sxml = XmlStoreProcedureRequest.generarXml(entidadDatosRequestArrayList);
        return entidadSistemaRepositorio.entidadDatosRegistroAsync(sxml)
                .thenApply(tarea -> {
                    List<EntidadDatos> datos = tarea.getKey();
                    StoreProcedureResponse respuesta = tarea.getValue();
                    return respuesta.getCodigo() == 200
                            ? new ResponseData<>(datos.isEmpty() ? null : datos.getFirst(), respuesta.getMensaje(), respuesta.getCodigo())
                            : new ResponseData<EntidadDatos>(null, respuesta.getMensaje(), respuesta.getCodigo());
                }).join();
    }
}
