package org.DevBusters.gestion_eventos.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.DevBusters.gestion_eventos.Entity.Usuarios;
import org.DevBusters.gestion_eventos.GestorInicioSesion;
import org.DevBusters.gestion_eventos.Service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.faces.view.ViewScoped;

import java.io.IOException;
import java.io.Serializable;

@Component("loginController")
@ViewScoped
@Data
public class LoginController implements Serializable {

    @Autowired
    private UsuarioService usuarioService;

    // Aquí inyectamos la instancia de GestorInicioSesion
    @Autowired
    private GestorInicioSesion gestorInicioSesion;

    private String nombreUsuario;
    private String contrasena;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostConstruct
    public void init() {
        // Usa la instancia inyectada
        if (gestorInicioSesion.estaAutenticado()) {
            try {
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect(".xhtml");
            } catch (IOException e) {
                logger.error("Error al redirigir", e);
            }
        }
    }

    public String iniciarSesion() {
        logger.info("Intento de login para usuario: " + nombreUsuario);

        try {
            Usuarios usuario = usuarioService.autenticar(nombreUsuario, contrasena);

            if (usuario != null) {
                // Usa la instancia inyectada
                gestorInicioSesion.iniciarSesion(usuario);
                logger.info("Login exitoso para: " + nombreUsuario);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "¡Bienvenido!",
                                "Has iniciado sesión correctamente"));

                return ".xhtml?faces-redirect=true";

            } else {
                logger.warn("Login fallido para: " + nombreUsuario);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error de Autenticación",
                                "Usuario o contraseña incorrectos"));
                return null;
            }

        } catch (Exception e) {
            logger.error("Error durante el login", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error del Sistema",
                            "Ha ocurrido un error inesperado"));
            return null;
        }
    }


    public void limpiarCampos() {
        this.nombreUsuario = "";
        this.contrasena = "";
    }
}