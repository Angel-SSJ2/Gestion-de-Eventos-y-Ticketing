package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository ticketrepository;

    @Override
    public List<TicketEntity> listarTicket() {
        List<TicketEntity> tickets = ticketrepository.findAll();
        return tickets;
    }
}

