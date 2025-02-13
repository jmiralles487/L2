package pe.gob.mtpe.rios.infraestructura.configuraciones;

public class ErrorCodes {

    // Errores generales
    public static final int ERROR_INESPERADO = 001;
    public static final int ERROR_CONFIGURACION = 002;
    public static final int ERROR_CONEXION = 003;
    public static final int ERROR_AUTENTICACION = 004;
    public static final int ERROR_AUTORIZACION = 005;

    // Errores de base de datos
    public static final int ERROR_CONEXION_BD = 101;
    public static final int ERROR_CONSULTA_SQL = 102;
    public static final int ERROR_INSERCION_DATOS = 103;
    public static final int ERROR_ACTUALIZACION_DATOS = 104;
    public static final int ERROR_ELIMINACION_DATOS = 105;

    // Errores de validación
    public static final int DATOS_INVALIDOS = 201;
    public static final int CAMPO_REQUERIDO = 202;
    public static final int FORMATO_INCORRECTO = 203;
    public static final int VALOR_FUERA_RANGO = 204;
    public static final int VALOR_DUPLICADO = 205;

    // Errores de negocio
    public static final int OPERACION_NO_PERMITIDA = 301;
    public static final int RECURSO_NO_ENCONTRADO = 302;
    public static final int LIMITE_EXCEDIDO = 303;
    public static final int ERROR_LOGICA_NEGOCIO = 304;
    public static final int ERROR_INTEGRACION_TERCEROS = 305;

    // Errores de API
    public static final int API_NO_ENCONTRADA = 401;
    public static final int METODO_HTTP_NO_SOPORTADO = 402;
    public static final int ERROR_PARSEO_JSON = 403;
    public static final int RECURSO_NO_ENCONTRADO_API = 404;
    public static final int ERROR_AUTORIZACION_API = 405;

    // Errores específicos de la aplicación
    public static final int ERROR_VALIDACION_USUARIO = 501;
    public static final int ERROR_CREACION_CUENTA = 502;
    public static final int ERROR_RECUPERACION_CONTRASENA = 503;
    public static final int ERROR_ACTUALIZACION_PERFIL = 504;
    public static final int ERROR_ELIMINACION_CUENTA = 505;

    // Errores de mapeo de datos
    public static final int COLUMNA_NO_ENCONTRADA = 901;
    public static final int ERROR_CONVERSION_TIPO_DATOS = 902;
    public static final int ERROR_LECTURA_DATOS = 903;
    public static final int ERROR_ESCRITURA_DATOS = 904;
    public static final int ERROR_MAPEO_DATOS = 905;
}
