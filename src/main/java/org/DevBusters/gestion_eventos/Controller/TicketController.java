package org.DevBusters.gestion_eventos.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.DevBusters.gestion_eventos.Enum;
import org.DevBusters.gestion_eventos.GestorInicioSesion;
import org.DevBusters.gestion_eventos.Repository.EventoRepository;
import org.DevBusters.gestion_eventos.Repository.UsuarioRepository;
import org.DevBusters.gestion_eventos.Repository.ITicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.faces.view.ViewScoped;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@Component("ticketController")
@ViewScoped
@Data
public class TicketController implements Serializable {

    @Autowired
    private GestorInicioSesion gestorInicioSesion;

    // Repositorios para buscar las entidades relacionadas y guardar el ticket
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ITicketRepository ticketRepository;

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    private Integer idEvento;
    private Double precio;
    private String mensaje;
    private TicketEntity nuevoTicket;

    @PostConstruct
    public void init() {
        if (!gestorInicioSesion.estaAutenticado()) {
            try {
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect("login.xhtml");
            } catch (IOException e) {
                logger.error("Error al redirigir a login", e);
            }
        }
    }

    public void comprarTicket() {
        logger.info("Intento de compra de ticket para el evento: " + idEvento);

        if (gestorInicioSesion.getUsuarioLogueado() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe iniciar sesión para comprar un ticket."));
            return;
        }

        try {
            // Obtener el usuario autenticado
            UsuarioEntity usuario = gestorInicioSesion.getUsuarioLogueado();

            // Buscar la entidad del evento en el repositorio
            Optional<EventoEntity> eventoOptional = eventoRepository.findById(idEvento);

            if (!eventoOptional.isPresent()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Evento no encontrado."));
                return;
            }

            EventoEntity evento = eventoOptional.get();

            nuevoTicket = new TicketEntity();
            nuevoTicket.setEvento(evento);
            nuevoTicket.setUsuario(usuario);
            nuevoTicket.setPrecio(precio);
            nuevoTicket.setEstado(Enum.VENDIDO);

            // Guardar el ticket directamente en el repositorio
            ticketRepository.save(nuevoTicket);

            mensaje = "¡Compra exitosa! Su ticket ha sido generado correctamente.";

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Compra Exitosa!", mensaje));

            logger.info("Ticket comprado para el usuario: " + usuario.getNombreUsuario());

        } catch (Exception e) {
            logger.error("Error durante la compra del ticket", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ha ocurrido un error al procesar su compra."));
        }
    }
}
