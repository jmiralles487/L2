package pe.gob.mtpe.rios.infraestructura.MapeosEntidades;

import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalReg;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaValidacion;
import pe.gob.mtpe.rios.infraestructura.configuraciones.InvalidOperationRuntimeException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapReaderToUsuarioSistema {

    // MapValidacionUsuario
    private static final String COL_N_NUM_USUARIOSISTEMA = "N_NUM_USUARIOSISTEMA";
    private static final String COL_ESTADO_USUARIO_ACTIVO = "ESTADO_USUARIO_ACTIVO";
    private static final String COL_EXISTE_REGISTRO = "EXISTE_REGISTRO";
    private static final String COL_V_CODPERSONAL = "V_CODPERSONAL";
    private static final String COL_V_CODUSU = "V_CODUSU";

    // MapDatosUsuario
    private static final String COL_CODPERFIL = "CODPERFIL";
    private static final String COL_CODPERSONAL = "CODPERSONAL";
    private static final String COL_ESTADO = "ESTADO";
    private static final String COL_NOMBRE_COMPLETO = "NOMBRE_COMPLETO";
    private static final String COL_NUMPERFIL = "NUMPERFIL";
    private static final String COL_PASSWORD = "PASSWORD";
    private static final String COL_CARGO = "CARGO";
    private static final String COL_CORREO = "CORREO";
    private static final String COL_TELEFONO = "TELEFONO";
    private static final String COL_TIPODOCID = "TIPODOCID";
    private static final String COL_APEMATERNO = "APEMATERNO";
    private static final String COL_APEPATERNO = "APEPATERNO";
    private static final String COL_NOMBRES = "NOMBRES";

    public UsuarioSistemaValidacion mapValidacionUsuario(ResultSet resultSet) throws SQLException {
        try {
            return new UsuarioSistemaValidacion(
                    resultSet.getInt(COL_N_NUM_USUARIOSISTEMA),
                    resultSet.getString(COL_V_CODUSU),
                    resultSet.getString(COL_V_CODPERSONAL),
                    resultSet.getInt(COL_EXISTE_REGISTRO),
                    resultSet.getString(COL_ESTADO_USUARIO_ACTIVO)
            );
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidOperationRuntimeException("Columna no encontrada en el ResultSet", e);
        } catch (SQLException e) {
            throw new InvalidOperationRuntimeException("Error al leer datos de validación de usuario", e);
        } catch (Exception e) {
            throw new InvalidOperationRuntimeException("Error inesperado al mapear usuario", e);
        }
    }

    public UsuarioSistemaDatos mapDatosUsuario(ResultSet resultSet) throws SQLException {
        try {
            return new UsuarioSistemaDatos(
                    resultSet.getString(COL_CODPERSONAL),
                    resultSet.getString(COL_PASSWORD),
                    resultSet.getString(COL_ESTADO),
                    resultSet.getString(COL_NOMBRE_COMPLETO),
                    resultSet.getInt(COL_NUMPERFIL),
                    resultSet.getString(COL_CODPERFIL),
                    resultSet.getString(COL_CORREO),
                    resultSet.getString(COL_TELEFONO),
                    resultSet.getString(COL_CARGO),
                    resultSet.getString(COL_TIPODOCID),
                    resultSet.getString(COL_NOMBRES),
                    resultSet.getString(COL_APEPATERNO),
                    resultSet.getString(COL_APEMATERNO)
            );
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidOperationRuntimeException("Columna no encontrada en el ResultSet", e);
        } catch (SQLException e) {
            throw new InvalidOperationRuntimeException("Error al leer datos de usuario", e);
        } catch (Exception e) {
            throw new InvalidOperationRuntimeException("Error inesperado al mapear usuario", e);
        }
    }

    public UsuarioSistemaTotalReg mapTotalRegistro(ResultSet resultSet) {
        try {
            return new UsuarioSistemaTotalReg(
                    resultSet.getObject("TOTALREGISTRO") == null ? 0 : resultSet.getInt("TOTALREGISTRO")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer datos de la base de datos", e);
        }
    }

    public UsuarioSistemaTotalDatos mapTotalDatos(ResultSet resultSet) {
        try {
            return new UsuarioSistemaTotalDatos(
                    resultSet.getObject("ID") == null ? 0 : resultSet.getInt("ID"),
                    resultSet.getObject("USUARIO") == null ? "" : resultSet.getString("USUARIO").trim(),
                    resultSet.getObject("NOMBRES") == null ? "" : resultSet.getString("NOMBRES").trim(),
                    resultSet.getObject("TELEFONO") == null ? "" : resultSet.getString("TELEFONO").trim(),
                    resultSet.getObject("CARGO") == null ? "" : resultSet.getString("CARGO").trim(),
                    resultSet.getObject("RUCENTIDAD") == null ? "" : resultSet.getString("RUCENTIDAD").trim(),
                    resultSet.getObject("ENTIDAD") == null ? "" : resultSet.getString("ENTIDAD").trim(),
                    resultSet.getObject("ROL") == null ? "" : resultSet.getString("ROL").trim(),
                    resultSet.getObject("ESTADO") == null ? "" : resultSet.getString("ESTADO").trim()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error al leer datos de la base de datos", e);
        }
    }

}