package pe.gob.mtpe.rios.Controllers.Rios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadDatosRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadLstRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.SRISLOSSYS.riostbc_entidad.EntidadTotalDatosResponse;
import pe.gob.mtpe.rios.aplicacion.interfaces.SRISLOSSYS.IEntidadSistemaServicio;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/entidad-sistema")
public class EntidadSistemaController {

    private final IEntidadSistemaServicio entidadSistemaServicio;

    @Autowired
    public EntidadSistemaController(IEntidadSistemaServicio entidadSistemaServicio) {
        this.entidadSistemaServicio = entidadSistemaServicio;
    }

    @PostMapping("/LstEntidad")
    public ResponseEntity<ResponseData<EntidadTotalDatosResponse>> LstEntidad(
            @RequestBody EntidadLstRegistroRequest request) throws Exception {

        ResponseData<EntidadTotalDatosResponse> responseData = new ResponseData<>();
        List<EntidadTotalDatos> datosRegistro = new ArrayList<>();
        int totalRegistro = 0;
        EntidadTotalDatosResponse respuesta = new EntidadTotalDatosResponse();

        try {
            var datosTotalRegistro = entidadSistemaServicio.totalRegistroEntidad(request);
            var datosRegistroList = entidadSistemaServicio.totalDatosRegEntidad(request);

            if (datosTotalRegistro != null && datosRegistroList != null) {
                if (datosTotalRegistro.getCodigo() != null && datosTotalRegistro.getCodigo() == 200) {
                    if (datosTotalRegistro.getData() != null) {
                        totalRegistro = datosTotalRegistro.getData().getTotalRegistro();
                    } else {
                        respuesta = new EntidadTotalDatosResponse();
                        responseData.setData(respuesta);
                        responseData.setMensaje("No se encontraron datos.");
                        responseData.setCodigo(404);
                        responseData.setSuccess(Boolean.FALSE);
                    }
                } else {
                    respuesta = new EntidadTotalDatosResponse();
                    responseData.setData(respuesta);
                    responseData.setMensaje(datosTotalRegistro.getMensaje());
                    responseData.setCodigo(datosTotalRegistro.getCodigo());
                    responseData.setSuccess(Boolean.FALSE);
                }
                if (datosRegistroList.getCodigo() != null && datosRegistroList.getCodigo() == 200) {
                    if (datosRegistroList.getData() != null) {
                        datosRegistro = datosRegistroList.getData();
                        respuesta.setCount(totalRegistro);
                        respuesta.setResults(datosRegistro);
                        responseData.setData(respuesta);
                        responseData.setMensaje("Consulta Exitosa");
                        responseData.setCodigo(200);
                        responseData.setSuccess(Boolean.TRUE);
                    } else {
                        respuesta = new EntidadTotalDatosResponse();
                        responseData.setData(respuesta);
                        responseData.setMensaje("No se encontraron datos.");
                        responseData.setCodigo(404);
                        responseData.setSuccess(Boolean.FALSE);
                    }
                } else {
                    respuesta = new EntidadTotalDatosResponse();
                    responseData.setData(respuesta);
                    responseData.setMensaje(datosRegistroList.getMensaje());
                    responseData.setCodigo(datosRegistroList.getCodigo());
                    responseData.setSuccess(Boolean.FALSE);
                }
            } else {
                respuesta = new EntidadTotalDatosResponse();
                responseData.setData(respuesta);
                responseData.setMensaje("El usuario ingresado no esta registrado para el uso del sistema.");
                responseData.setCodigo(404);
                responseData.setSuccess(Boolean.FALSE);
            }
        } catch (Exception e) {
            respuesta = new EntidadTotalDatosResponse();
            responseData.setData(respuesta);
            responseData.setMensaje("Error inesperado: " + e.getMessage());
            responseData.setCodigo(500);
            responseData.setSuccess(Boolean.FALSE);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @PostMapping("/EntidadDatos")
    public ResponseEntity<ResponseData<EntidadDatos>> LstEntidad(
            @RequestBody EntidadDatosRequest request) throws Exception
    {
        ResponseData<EntidadDatos> responseData = new ResponseData<>();
        EntidadDatos respuesta = new EntidadDatos();
        try{
            var datosEntidad= entidadSistemaServicio.datosRegistroEntidad(request);
            if (datosEntidad != null) {

                if (datosEntidad.getData() != null) {
                    return new ResponseEntity<>(datosEntidad, HttpStatus.OK);
                }
                else {
                    responseData.setData(null);
                    responseData.setMensaje("No se encontro el registro seleccionado para el uso del sistema.");
                    responseData.setCodigo(201);
                    responseData.setSuccess(Boolean.FALSE);
                }
            }
            else
            {
                respuesta = new EntidadDatos();
                responseData.setData(respuesta);
                responseData.setMensaje("No se encontraron datos.");
                responseData.setCodigo(404);
                responseData.setSuccess(Boolean.FALSE);
            }
        }
        catch (Exception e) {
            respuesta = new EntidadDatos();
            responseData.setData(respuesta);
            responseData.setMensaje("Error inesperado: " + e.getMessage());
            responseData.setCodigo(500);
            responseData.setSuccess(Boolean.FALSE);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
