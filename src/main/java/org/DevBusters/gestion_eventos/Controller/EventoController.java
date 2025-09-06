package org.DevBusters.gestion_eventos.Controller;

import org.DevBusters.gestion_eventos.Enum;
import org.DevBusters.gestion_eventos.Repository.TicketRepository;
import org.DevBusters.gestion_eventos.Service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/estadisticas/{idEvento}")
    @ResponseBody
    public String estadisticasEvento(@PathVariable Integer idEvento) {
        Long vendidos = ticketRepository.countByEvento_IdEventoAndEstado(idEvento, Enum.VENDIDO);
        return "Tickets vendidos: " + vendidos;
    }
}
