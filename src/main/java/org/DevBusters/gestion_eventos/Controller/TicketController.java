package org.DevBusters.gestion_eventos.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
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
import java.util.List;
import java.util.Optional;
@Named
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
        if (gestorInicioSesion.getUsuarioLogueado() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe iniciar sesión para comprar un ticket."));
            return;
        }

        UsuarioEntity usuario = gestorInicioSesion.getUsuario();
        nuevoTicket.setUsuario(usuario);
        logger.info("Intento de compra de ticket para el evento: " + (nuevoTicket.getEvento() != null ? nuevoTicket.getEvento().getIdEvento() : "null"));

        try {
            if (nuevoTicket.getEvento() == null){
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar un evento."));
                return;
            }
            nuevoTicket.setEstado(Enum.Vendido);

            ticketRepository.save(nuevoTicket);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Compra Exitosa!", "Su ticket ha sido generado correctamente."));

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

            if (ticket.getEstado() == Enum.Usado) {
                this.resultadoValidacion = "El ticket ya ha sido validado.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Ticket Duplicado", this.resultadoValidacion));
            } else {
                ticket.setEstado(Enum.Usado);
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

    private List<TicketEntity> historialUsuario;

    public List<TicketEntity> getHistorialUsuario() {
        // opcional: cargar los tickets del usuario logueado
        if (historialUsuario == null && gestorInicioSesion.getUsuario() != null) {
            historialUsuario = ticketRepository.findByUsuario(gestorInicioSesion.getUsuario());
        }
        return historialUsuario;
    }

}
