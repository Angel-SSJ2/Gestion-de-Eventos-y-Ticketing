package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.entidad.TicketEntity;
import org.DevBusters.gestion_eventos.Repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private ITicketRepository ticketRepository;

    @Override
    public TicketEntity crearTicket(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<TicketEntity> buscarPorId(Integer id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<TicketEntity> buscarTodos() {
        return ticketRepository.findAll();
    }

    @Override
    public void eliminarTicket(Integer id) {
        ticketRepository.deleteById(id);
    }
}
