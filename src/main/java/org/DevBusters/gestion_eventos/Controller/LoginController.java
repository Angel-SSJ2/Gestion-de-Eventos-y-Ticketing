package org.DevBusters.gestion_eventos.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.DevBusters.gestion_eventos.GestorInicioSesion;
import org.DevBusters.gestion_eventos.Service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import javax.security.auth.login.LoginException;

@Component("loginController")
@ViewScoped
@Data
public class LoginController implements Serializable {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private GestorInicioSesion gestorInicioSesion;

    private String nombreUsuario;
    private String contrasena;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostConstruct
    public void init() {
        if (gestorInicioSesion.estaAutenticado()) {
            try {
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/home.xhtml");
            } catch (IOException e) {
                logger.error("Error al redirigir al usuario autenticado", e);
            }
        }
    }

    public String iniciarSesion() {
        logger.info("Intento de login para usuario: {}", nombreUsuario);

        if (nombreUsuario == null || nombreUsuario.trim().isEmpty() || contrasena == null || contrasena.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Autenticación", "Por favor, complete todos los campos."));
            return null;
        }

        try {
            UsuarioEntity usuario = usuarioService.autenticar(nombreUsuario, contrasena);

            if (usuario != null) {
                gestorInicioSesion.iniciarSesion(usuario);
                logger.info("Login exitoso para: {}", nombreUsuario);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Bienvenido!", "Has iniciado sesión correctamente."));

                return "home.xhtml?faces-redirect=true";

            } else {
                logger.warn("Login fallido para: {}", nombreUsuario);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Autenticación", "Usuario o contraseña incorrectos."));
                return null;
            }

        } catch (Exception e) {
            logger.error("Error inesperado durante el login", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error del Sistema", "Ha ocurrido un error inesperado."));
            return null;
        }
    }

    public void limpiarCampos() {
        this.nombreUsuario = null;
        this.contrasena = null;
    }
}