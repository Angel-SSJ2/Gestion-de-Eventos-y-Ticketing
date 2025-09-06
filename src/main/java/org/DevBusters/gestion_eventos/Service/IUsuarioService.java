package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    UsuarioEntity autenticar(String nombreUsuario, String contrasena);
    Optional<UsuarioEntity> buscarPornombreUsuario(String nombreUsuario);
    Optional<UsuarioEntity> buscarUsuarioPorId(Integer idCliente);

    UsuarioEntity crearUsuario(UsuarioEntity usuario);

    boolean existenombreUsuario(String nombreUsuario);
    boolean existeCorreo(String correo);

    List<UsuarioEntity> listarUsuarios();
    void eliminarUsuario(Integer idCliente);
}