package pe.gob.mtpe.rios.infraestructura.configuraciones;

public class InvalidOperationRuntimeException extends RuntimeException {
    public InvalidOperationRuntimeException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
