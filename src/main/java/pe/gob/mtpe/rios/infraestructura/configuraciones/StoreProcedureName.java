package pe.gob.mtpe.rios.infraestructura.configuraciones;

public class StoreProcedureName {
    private String esquema;
    private String nombrePaquete;
    private String nombreProcedimiento;

    public StoreProcedureName(String esquema, String nombrePaquete, String nombreProcedimiento) {
        this.esquema = esquema;
        this.nombrePaquete = nombrePaquete;
        this.nombreProcedimiento = nombreProcedimiento;
    }

    public String getProcedureCall() {
        return String.format("BEGIN %s.%s.%s(?, ?); END;", esquema, nombrePaquete, nombreProcedimiento);
    }
}
