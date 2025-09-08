package org.DevBusters.gestion_eventos.Controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.DevBusters.gestion_eventos.Entity.OrganizadorEntity;
import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.DevBusters.gestion_eventos.Service.IOrganizadorService;
import org.DevBusters.gestion_eventos.Service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("registroController")
@ViewScoped
@Data
public class RegistroController implements Serializable {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrganizadorService organizadorService;

    private UsuarioEntity usuarioSeleccionado;
    private OrganizadorEntity organizadorSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);

    public RegistroController() {
        this.usuarioSeleccionado = new UsuarioEntity();
        this.organizadorSeleccionado = new OrganizadorEntity();
    }

    public String registrarCliente() {
        try {

            if (usuarioService.buscarPornombreUsuario(usuarioSeleccionado.getNombreUsuario()).isPresent() ||
                    usuarioService.existeCorreo(usuarioSeleccionado.getCorreo())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Registro", "El usuario o correo ya están registrados."));
                return null;
            }

            this.usuarioService.crearUsuario(this.usuarioSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "¡Bienvenido " + this.usuarioSeleccionado.getNombreUsuario() + "!"));

            logger.info("Cliente registrado: " + this.usuarioSeleccionado.getCorreo());

            return "login.xhtml?faces-redirect=true";

        } catch (Exception e) {
            logger.warn("Error al registrar cliente: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar el cliente"));
            return null;
        }
    }

    public String registrarOrganizador() {
        try {

            if (organizadorService.buscarPorNombreOrganizador(organizadorSeleccionado.getNombreOrganizador()).isPresent() ||
                    organizadorService.existeCorreo(organizadorSeleccionado.getCorreo())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de Registro", "El organizador o correo ya están registrados."));
                return null;
            }

            this.organizadorService.crearOrganizador(this.organizadorSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "¡Bienvenido " + this.organizadorSeleccionado.getNombreOrganizador() + "!"));

            logger.info("Organizador registrado: " + this.organizadorSeleccionado.getCorreo());

            return "login.xhtml?faces-redirect=true";

        } catch (Exception e) {
            logger.warn("Error al registrar organizador: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar el organizador"));
            return null;
        }
    }
}