package pe.gob.mtpe.rios.Controllers.Rios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_entidad.EntidadLstRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios.UsuarioLstRegistroRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.SRISLOSSYS.riostbc_entidad.EntidadTotalDatosResponse;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.SRISLOSSYS.riostbc_usuarios.UsuarioTotalDatosResponse;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalDatos;
import pe.gob.mtpe.rios.seguridad.JwtUtils2;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios.UsuarioSistemaCredencialesRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Request.SRISLOSSYS.riostbc_usuarios.UsuarioSistemaValidacionRequest;
import pe.gob.mtpe.rios.aplicacion.dtos.Response.SRISLOSSYS.riostbc_usuarios.UsuarioSistemaLoginAdmResponse;
import pe.gob.mtpe.rios.aplicacion.interfaces.SRISLOSSYS.IUsuarioSistemaServicio;
import pe.gob.mtpe.rios.dominio.agregados.ResponseData;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaDatos;
import pe.gob.mtpe.rios.infraestructura.configuraciones.DecodePassword;

import java.util.*;

@RestController
@RequestMapping("/api/usuario-sistema")
public class UsuarioSistemaController {

    @Value("${llavejwt}")
    private String jwtSecret;

    private final IUsuarioSistemaServicio usuarioSistemaService;

    @Autowired
    public UsuarioSistemaController(IUsuarioSistemaServicio usuarioSistemaService) {
        this.usuarioSistemaService = usuarioSistemaService;
    }

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<ResponseData<UsuarioSistemaLoginAdmResponse>> loginUsuarioAdm(
            @RequestBody UsuarioSistemaCredencialesRequest request) {

        UsuarioSistemaLoginAdmResponse usuarioSistemaDatos = new UsuarioSistemaLoginAdmResponse();
        ResponseData<UsuarioSistemaLoginAdmResponse> dataResponse;

        UsuarioSistemaValidacionRequest usuarioSistemaValidacionRequest = new UsuarioSistemaValidacionRequest();
        usuarioSistemaValidacionRequest.setCodUsuario(request.getUsername());

        try {
            // Validación del usuario
            var validacion = usuarioSistemaService.validarUsuarioSistema(usuarioSistemaValidacionRequest);
            if (validacion != null && Objects.requireNonNull(validacion.getData()).getExisteRegistro()>0) {

                assert validacion.getData().getEstadoUsuarioActivo() != null;
                if (validacion.getData().getEstadoUsuarioActivo().equals("1")) {
                    var datos = usuarioSistemaService.datosUsuarioSistema(request);

                    if (datos != null) {
                        String decodedPassword = DecodePassword.decode(request.getUsername(), datos.getData().getPassword());

                        if (decodedPassword != null) {
                            // Generar JWT
                            String token = generarTokenJwt(datos.getData(), request.getUsername(), validacion.getData().getNumUsuariosistema().toString());

                            usuarioSistemaDatos.setCodPerfil(datos.getData().getCodperfil());
                            usuarioSistemaDatos.setCodPersonal(datos.getData().getCodpersonal());
                            usuarioSistemaDatos.setNombresCompleto(datos.getData().getNombreCompleto());
                            usuarioSistemaDatos.setNumPerfil(datos.getData().getNumperfil());
                            usuarioSistemaDatos.setNumUsuarioSistema(validacion.getData().getNumUsuariosistema());
                            usuarioSistemaDatos.setToken(token);

                            dataResponse = new ResponseData<>(usuarioSistemaDatos, "Consulta Exitosa", 200);
                        } else {
                            dataResponse = new ResponseData<>(usuarioSistemaDatos, "La contraseña ingresada es incorrecta.", 404);
                        }
                    } else {
                        dataResponse = new ResponseData<>(usuarioSistemaDatos, "El usuario no está registrado para el uso del sistema.", 404);
                    }
                } else {
                    dataResponse = new ResponseData<>(usuarioSistemaDatos, "El usuario ingresado se encuentra inactivo.", 400);
                }
            } else {
                dataResponse = new ResponseData<>(usuarioSistemaDatos, "El usuario no está registrado para el uso del sistema.", 404);
            }
        } catch (Exception ex) {
            dataResponse = new ResponseData<>(usuarioSistemaDatos, "Error inesperado: " + ex.getMessage(), 500);
        }

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping("/LstUsuario")
    public ResponseEntity<ResponseData<UsuarioTotalDatosResponse>> LstEntidad(
            @RequestBody UsuarioLstRegistroRequest request) throws Exception {

        ResponseData<UsuarioTotalDatosResponse> responseData = new ResponseData<>();
        List<UsuarioSistemaTotalDatos> datosRegistro = new ArrayList<>();
        int totalRegistro = 0;
        UsuarioTotalDatosResponse respuesta = new UsuarioTotalDatosResponse();

        try {
            var datosTotalRegistro = usuarioSistemaService.totalRegistroUsuario(request);
            var datosRegistroList = usuarioSistemaService.totalDatosRegUsuario(request);

            if (datosTotalRegistro != null && datosRegistroList != null) {
                if (datosTotalRegistro.getCodigo() != null && datosTotalRegistro.getCodigo() == 200) {
                    if (datosTotalRegistro.getData() != null) {
                        totalRegistro = datosTotalRegistro.getData().getTotalRegistro();
                    } else {
                        respuesta = new UsuarioTotalDatosResponse();
                        responseData.setData(respuesta);
                        responseData.setMensaje("No se encontraron datos.");
                        responseData.setCodigo(404);
                        responseData.setSuccess(Boolean.FALSE);
                    }
                } else {
                    respuesta = new UsuarioTotalDatosResponse();
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
                        respuesta = new UsuarioTotalDatosResponse();
                        responseData.setData(respuesta);
                        responseData.setMensaje("No se encontraron datos.");
                        responseData.setCodigo(404);
                        responseData.setSuccess(Boolean.FALSE);
                    }
                } else {
                    respuesta = new UsuarioTotalDatosResponse();
                    responseData.setData(respuesta);
                    responseData.setMensaje(datosRegistroList.getMensaje());
                    responseData.setCodigo(datosRegistroList.getCodigo());
                    responseData.setSuccess(Boolean.FALSE);
                }
            } else {
                respuesta = new UsuarioTotalDatosResponse();
                responseData.setData(respuesta);
                responseData.setMensaje("El usuario ingresado no esta registrado para el uso del sistema.");
                responseData.setCodigo(404);
                responseData.setSuccess(Boolean.FALSE);
            }
        } catch (Exception e) {
            respuesta = new UsuarioTotalDatosResponse();
            responseData.setData(respuesta);
            responseData.setMensaje("Error inesperado: " + e.getMessage());
            responseData.setCodigo(500);
            responseData.setSuccess(Boolean.FALSE);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    private String generarTokenJwt(UsuarioSistemaDatos usuarioDatos, String username, String idUsuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("NUMPERFIL", usuarioDatos.getNumperfil());
        claims.put("NOMBRE_COMPLETO", usuarioDatos.getNombreCompleto());
        claims.put("CODPERFIL", usuarioDatos.getCodperfil());
        claims.put("USERNAME", username);
        claims.put("CORREO", usuarioDatos.getCorreo());
        claims.put("TELEFONO", usuarioDatos.getTelefono());
        claims.put("CARGO", usuarioDatos.getCargo());
        claims.put("TIPODOCID", usuarioDatos.getTipodocid());
        claims.put("NOMBRES", usuarioDatos.getNombres());
        claims.put("APEPATERNO", usuarioDatos.getApepaterno());
        claims.put("APEMATERNO", usuarioDatos.getApematerno());
        claims.put("CODPERSONAL", usuarioDatos.getCodpersonal());
        claims.put("ID", idUsuario);
        claims.put("sub", username);

        return JwtUtils2.generateToken(claims, jwtSecret, jwtExpiration);
    }
}
