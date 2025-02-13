package pe.gob.mtpe.rios.infraestructura.configuraciones;

import pe.gob.mtpe.rios.dominio.agregados.StoreProcedureResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorHelper {
    public static StoreProcedureResponse handleError(Exception ex, int codigoError) {
        return new StoreProcedureResponse(
                codigoError,
                ex.getMessage()
        );
    }
}