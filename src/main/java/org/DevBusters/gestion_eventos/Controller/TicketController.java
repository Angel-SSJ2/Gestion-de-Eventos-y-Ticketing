package org.DevBusters.gestion_eventos.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.DevBusters.gestion_eventos.Entity.EventoEntity;
import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.DevBusters.gestion_eventos.Enum;
import org.DevBusters.gestion_eventos.GestorInicioSesion;
import org.DevBusters.gestion_eventos.Repository.EventoRepository;
import org.DevBusters.gestion_eventos.Repository.TicketRepository;
import org.DevBusters.gestion_eventos.Service.ITicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@Component("ticketController")
@ViewScoped
@Data
public class TicketController implements Serializable {

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private GestorInicioSesion gestorInicioSesion;

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    private String ticketId;
    private String resultadoValidacion;

    private TicketEntity nuevoTicket;
    private TicketEntity ticketSeleccionado;

    private Integer idEvento; // para saber a qué evento se compra el ticket
    private Double precio;
    private String mensaje;

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
        this.nuevoTicket = new TicketEntity();
    }

    /**
     * Comprar ticket
     */
    public void comprarTicket() {
        logger.info("Intento de compra de ticket para el evento: " + idEvento);

        if (gestorInicioSesion.getUsuarioLogueado() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe iniciar sesión para comprar un ticket."));
            return;
        }

        try {
            UsuarioEntity usuario = gestorInicioSesion.getUsuarioLogueado();

            Optional<EventoEntity> eventoOptional = eventoRepository.findById(idEvento);
            if (eventoOptional.isEmpty()) {
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

            ticketRepository.save(nuevoTicket);

            mensaje = "¡Compra exitosa! Su ticket ha sido generado correctamente.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Compra Exitosa!", mensaje));

            logger.info("Ticket comprado para el usuario: " + usuario.getNombreUsuario());

            // Reset
            this.nuevoTicket = new TicketEntity();

        } catch (Exception e) {
            logger.error("Error durante la compra del ticket", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ha ocurrido un error al procesar su compra."));
        }
    }

    /**
     * Validar ticket en la entrada
     */
    public void validarTicket() {
        Optional<TicketEntity> ticketOpt = ticketService.buscarTicketPorId(Integer.parseInt(this.ticketId));

        if (ticketOpt.isPresent()) {
            TicketEntity ticket = ticketOpt.get();

            if (ticket.getEstado() == Enum.USADO) {
                this.resultadoValidacion = "El ticket ya ha sido validado.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Ticket Duplicado", this.resultadoValidacion));
            } else {
                ticket.setEstado(Enum.USADO);
                ticketService.guardarTicket(ticket);
                this.resultadoValidacion = "El ticket es válido. Acceso concedido.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Válido", this.resultadoValidacion));
            }
        } else {
            this.resultadoValidacion = "El ticket no es válido.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ticket Inválido", this.resultadoValidacion));
        }

        this.ticketId = null;
    }

    public void buscarTicketParaMostrar(Integer id) {
        this.ticketSeleccionado = ticketService.buscarTicketPorId(id).orElse(null);
    }
}
