package pe.gob.mtpe.rios.dominio.agregados;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {
    @Nullable
    private Integer codigo;
    @Nullable
    private String mensaje;
    @Nullable
    private Boolean success;
    @Nullable
    private T data;

    public ResponseData(@Nullable T data,@Nullable String mensaje) {
        this.success = true;
        this.mensaje = mensaje;
        this.data = data;
        this.codigo = 200;
    }

    public ResponseData(@Nullable T datos,@Nullable String mensaje,@Nullable Integer codigo) {
        this.data = datos;
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.success = true;
    }

    public ResponseData(@Nullable String mensaje) {
        this.codigo = 203;
        this.success = false;
        this.mensaje = mensaje;
    }
}