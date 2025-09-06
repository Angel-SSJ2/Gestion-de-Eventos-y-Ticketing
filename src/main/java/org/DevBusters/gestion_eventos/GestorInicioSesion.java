package org.DevBusters.gestion_eventos;

import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class GestorInicioSesion implements Serializable {

    private UsuarioEntity usuarioLogueado;
    private boolean autenticado = false;

    public void iniciarSesion(UsuarioEntity usuario) {
        this.usuarioLogueado = usuario;
        this.autenticado = true;
    }

    public void cerrarSesion() {
        this.usuarioLogueado = null;
        this.autenticado = false;
    }

    public boolean estaAutenticado() {
        return autenticado && usuarioLogueado != null;
    }

    public UsuarioEntity getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public String getUsername() {
        return usuarioLogueado != null ? usuarioLogueado.getNombreUsuario() : "";
    }
}
