
package org.DevBusters.gestion_eventos.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Enum;
import org.DevBusters.gestion_eventos.Service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Optional;

@Component("ticketController")
@ViewScoped
@Data
public class TicketController implements Serializable {

    @Autowired
    private ITicketService ticketService;

    private String ticketId;
    private String resultadoValidacion;
    private TicketEntity nuevoTicket;
    private TicketEntity ticketSeleccionado;

    @PostConstruct
    public void init() {
        this.nuevoTicket = new TicketEntity();
    }

    public String comprarTicket() {
        try {
            nuevoTicket.setEstado(Enum.DISPONIBLE);

            ticketService.guardarTicket(nuevoTicket);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "¡Ticket comprado con éxito!"));

            this.nuevoTicket = new TicketEntity();

            return "ComprarTicket.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo comprar el ticket."));
            e.printStackTrace();
            return null;
        }
        }

    public void validarTicket() {
        Optional<TicketEntity> ticketOpt = ticketService.getTicketById(Integer.parseInt(this.ticketId));

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
        this.ticketSeleccionado = ticketService.getTicketById(id).orElse(null);
    }
}