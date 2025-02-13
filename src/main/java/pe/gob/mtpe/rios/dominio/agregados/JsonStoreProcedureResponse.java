package pe.gob.mtpe.rios.dominio.agregados;

import lombok.Data;

import java.util.List;

@Data
public class JsonStoreProcedureResponse<T> {
    private List<T> storeParametros;
}