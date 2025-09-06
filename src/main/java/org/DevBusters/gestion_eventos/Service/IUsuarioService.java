package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.entidad.Usuario;

public interface IUsuarioService {
    Usuario autenticar(String nombreUsuario, String contrasena);

    Usuario buscarPorUsername(String nombreUsuario);

    Usuario crearUsuario(Usuario usuario);

    boolean existeUsername(String nombreUsuario);

    boolean existeEmail(String correo);
}