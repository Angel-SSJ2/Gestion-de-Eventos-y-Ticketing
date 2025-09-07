package org.DevBusters.gestion_eventos;

import org.DevBusters.gestion_eventos.Entity.OrganizadorEntity;
import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class GestorInicioSesion implements Serializable {

    private Object usuarioLogueado;
    private static boolean autenticado = false;
    private UsuarioEntity usuario;

    public void iniciarSesion(Object usuario) {
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

    public Object getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public UsuarioEntity getUsuario(){
        if (usuarioLogueado instanceof UsuarioEntity){
            return (UsuarioEntity) usuarioLogueado;
        }
        return null;
    }

    public String getNombreUsuario() {
        if (usuarioLogueado instanceof UsuarioEntity){
            return ((UsuarioEntity) usuarioLogueado).getNombreUsuario();
        } else if (usuarioLogueado instanceof OrganizadorEntity){
            return ((OrganizadorEntity) usuarioLogueado).getNombreOrganizador();
        } else {
            return "";
        }
    }

    public boolean esUsuario(){
        return usuarioLogueado instanceof UsuarioEntity;
    }

    public boolean esOrganizador(){
        return usuarioLogueado instanceof OrganizadorEntity;
    }
}
