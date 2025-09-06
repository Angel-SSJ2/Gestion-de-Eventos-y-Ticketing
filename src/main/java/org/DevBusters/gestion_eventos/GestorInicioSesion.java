package org.DevBusters.gestion_eventos;

import org.DevBusters.gestion_eventos.entity.Usuarios;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class GestorInicioSesion implements Serializable {

    private static Usuarios usuarioLogueado;
    private static boolean autenticado = false;

    public void iniciarSesion(Usuarios usuario) {
        this.usuarioLogueado = usuario;
        this.autenticado = true;
    }

    public void cerrarSesion() {
        this.usuarioLogueado = null;
        this.autenticado = false;
    }

    public static boolean estaAutenticado() {
        return autenticado && usuarioLogueado != null;
    }

    public Usuarios getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public String getNombreUsuario() {
        return usuarioLogueado != null ? usuarioLogueado.getNombreUsuario() : "";
    }

    public static String getUsername() {
        return usuarioLogueado != null ? usuarioLogueado.getNombreUsuario(): "";
    }
}
