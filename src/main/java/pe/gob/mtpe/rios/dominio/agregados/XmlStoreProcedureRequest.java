package pe.gob.mtpe.rios.dominio.agregados;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class XmlStoreProcedureRequest {
    public static String generarXml(List<?> request) throws Exception {
        XmlStoreProcedureParametros<XmlStoreProcedureParametros.ListStoreParametros<?>> xml = new XmlStoreProcedureParametros<>();
        xml.setROOT(new XmlStoreProcedureParametros.ListStoreParametros<>());
        assert xml.getROOT() != null;
        xml.getROOT().setStoreParametros((List<XmlStoreProcedureParametros.ListStoreParametros<?>>) request);

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(xml);

        XmlMapper xmlMapper = new XmlMapper();
        Object objeto = new ObjectMapper().readValue(json, Object.class);
        String xmlresult = xmlMapper.writeValueAsString(objeto);
        xmlresult = xmlresult.replace("<LinkedHashMap>", "").replace("</LinkedHashMap>", "");
        return xmlresult;
    }
}