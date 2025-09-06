package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;

public interface IUsuarioService {
    UsuarioEntity autenticar(String nombreUsuario, String contrasena);

    UsuarioEntity buscarPorUsername(String nombreUsuario);

    UsuarioEntity crearUsuario(UsuarioEntity usuario);

    boolean existeUsername(String nombreUsuario);

    boolean existeEmail(String correo);
}