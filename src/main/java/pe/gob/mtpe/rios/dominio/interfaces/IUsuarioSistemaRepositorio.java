package pe.gob.mtpe.rios.dominio.interfaces;

import pe.gob.mtpe.rios.dominio.agregados.StoreProcedureResponse;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalDatos;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaTotalReg;
import pe.gob.mtpe.rios.dominio.entidades.riostbc_usuarios.UsuarioSistemaValidacion;

import javafx.util.Pair;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface IUsuarioSistemaRepositorio {
    CompletableFuture<Pair<List<UsuarioSistemaValidacion>, StoreProcedureResponse>> usrSistemaValidacionAsync(String jsonInput);

    CompletableFuture<Pair<List<UsuarioSistemaDatos>, StoreProcedureResponse>> usrSistemaDatosAsync(String jsonInput);

    CompletableFuture<Pair<List<UsuarioSistemaTotalReg>, StoreProcedureResponse>> usrTotalRegistroAsync(String jsonInput);
    CompletableFuture<Pair<List<UsuarioSistemaTotalDatos>, StoreProcedureResponse>> usrTotalDatosAsync(String jsonInput);
}
