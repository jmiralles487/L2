package pe.gob.mtpe.rios.infraestructura.MapeosEntidades;

import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadExisteRegistro;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_entidad.EntidadTotalReg;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapReaderToEntidad {
    public EntidadTotalReg mapTotalRegistro(ResultSet resultSet) {
        try {
            return new EntidadTotalReg(
                    resultSet.getObject("TOTALREGISTRO") == null ? 0 : resultSet.getInt("TOTALREGISTRO")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer datos de la base de datos", e);
        }
    }

    public EntidadTotalDatos mapTotalDatos(ResultSet resultSet) {
        try {
            return new EntidadTotalDatos(
                    resultSet.getObject("ID") == null ? 0 : resultSet.getInt("ID"),
                    resultSet.getObject("RUC") == null ? "" : resultSet.getString("RUC").trim(),
                    resultSet.getObject("RAZON_SOCIAL") == null ? "" : resultSet.getString("RAZON_SOCIAL").trim(),
                    resultSet.getObject("DOMICILIO") == null ? "" : resultSet.getString("DOMICILIO").trim(),
                    resultSet.getObject("CODIGOUNIDAD") == null ? "" : resultSet.getString("CODIGOUNIDAD").trim(),
                    resultSet.getObject("CORREO") == null ? "" : resultSet.getString("CORREO").trim(),
                    resultSet.getObject("TELEFONO") == null ? "" : resultSet.getString("TELEFONO").trim(),
                    resultSet.getObject("ACTIVO") == null ? "" : resultSet.getString("ACTIVO").trim()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer datos de la base de datos", e);
        }
    }

    public EntidadExisteRegistro mapExisteRegistro(ResultSet resultSet) {
        try {
            return new EntidadExisteRegistro(
                    resultSet.getObject("EXISTEREGISTRO") == null ? 0 : resultSet.getInt("EXISTEREGISTRO")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer datos de la base de datos", e);
        }
    }

    public EntidadDatos mapDatos(ResultSet resultSet) {
        try {
            return new EntidadDatos(
                    resultSet.getObject("NumEntidad") == null ? 0 : resultSet.getInt("NumEntidad"),
                    resultSet.getObject("Nombre") == null ? "" : resultSet.getString("Nombre").trim(),
                    resultSet.getObject("Domicilio") == null ? "" : resultSet.getString("Domicilio").trim(),
                    resultSet.getObject("CodEntidad") == null ? "" : resultSet.getString("CodEntidad").trim(),
                    resultSet.getObject("RucEntidad") == null ? "" : resultSet.getString("RucEntidad").trim(),
                    resultSet.getObject("Correo") == null ? "" : resultSet.getString("Correo").trim(),
                    resultSet.getObject("Telefono") == null ? 0 : resultSet.getInt("Telefono"),
                    resultSet.getObject("Activo") == null ? "" : resultSet.getString("Activo").trim()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer datos de la base de datos", e);
        }
    }

}
