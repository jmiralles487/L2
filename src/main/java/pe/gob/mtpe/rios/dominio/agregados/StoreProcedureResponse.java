package pe.gob.mtpe.rios.dominio.agregados;

import lombok.Data;

@Data
public class StoreProcedureResponse {
    private Integer codigo;
    private String mensaje;

    public StoreProcedureResponse() {}

    public StoreProcedureResponse(Integer codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
}
