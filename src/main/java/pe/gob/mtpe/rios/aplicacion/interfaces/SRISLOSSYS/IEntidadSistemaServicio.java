package pe.gob.mtpe.rios.aplicacion.interfaces.SRISLOSSYS;

import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadDatosRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadLstRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadRucRegistroRequest;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadExisteRegistro;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalReg;

import java.util.List;

public interface IEntidadSistemaServicio {
    ResponseData<EntidadTotalReg> totalRegistroEntidad(EntidadLstRegistroRequest entidadLstRegistroRequest) throws Exception;
    ResponseData<List<EntidadTotalDatos>> totalDatosRegEntidad(EntidadLstRegistroRequest entidadLstRegistroRequest) throws Exception;
    ResponseData<EntidadExisteRegistro> existeRegistroEntidad(EntidadRucRegistroRequest entidadRucRegistroRequest) throws Exception;
    ResponseData<EntidadDatos> datosRegistroEntidad(EntidadDatosRequest entidadDatosRequest) throws Exception;
}
