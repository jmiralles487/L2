package pe.gob.mtpe.rios.dominio.agregados;

import lombok.Data;
import org.springframework.lang.Nullable;
import java.util.List;

@Data
public class XmlStoreProcedureParametros<T> {
    @Nullable
    private ListStoreParametros<T> ROOT;

    @Data
    public static class ListStoreParametros<T> {
        @Nullable
        private List<T> StoreParametros;
    }
}

