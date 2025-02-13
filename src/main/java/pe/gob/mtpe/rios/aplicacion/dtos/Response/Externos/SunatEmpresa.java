package pe.gob.mtpe.rios.aplicacion.dtos.Response.Externos;

import lombok.Data;

@Data
public class SunatEmpresa {
    private String ruc;
    private String razonSocial;
    private String domicilioLegal;
    private String[] nombreRepresentantes;
    private String ubigeo;
    private String fechaAlta;
    private String descripcionCiiu;
    private String codigoCiiu;
    private String codigoDepartamento;
    private String codigoDistrito;
    private String codigoProvincia;
    private String descripcionDepartamento;
    private String descripcionDistrito;
    private String descripcionProvincia;
    private String codigoTipoVia;
    private String descripcionTipoVia;
    private String nombreVia;
    private String codigoTipoZona;
    private String descripcionTipoZona;
    private String descripcionZona;
    private String manzana;
    private String lote;
    private String numeroDepartamento;
    private String interno;
    private String codigoTipoEmpresa;
    private String descripcionTipoEmpresa;
}
