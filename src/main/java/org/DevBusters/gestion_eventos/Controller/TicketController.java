
package org.DevBusters.gestion_eventos.Controller;

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
}