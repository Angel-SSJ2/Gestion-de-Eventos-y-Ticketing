package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.entity.Usuarios;

import java.util.List;

public interface IUsuarioService {
    Usuarios autenticar(String nombreUsuario, String contrasena);

    Usuarios buscarPorUsername(String nombreUsuario);

    Usuarios crearUsuario(Usuarios usuario);

    boolean existeUsername(String nombreUsuario);

    boolean existeEmail(String correo);
}
