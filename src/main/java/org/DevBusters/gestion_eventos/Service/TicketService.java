package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.EventoEntity;
import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.DevBusters.gestion_eventos.Repository.EventoRepository;
import org.DevBusters.gestion_eventos.Repository.TicketRepository;
import org.DevBusters.gestion_eventos.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.DevBusters.gestion_eventos.Enum;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository ticketrepository;

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<TicketEntity> listarTicket() {
        List<TicketEntity> tickets = ticketrepository.findAll();
        return tickets;
    }

    @Override
    public Map<String, Object> validarTicketPorQr(String codigoQr) {
        return Map.of();
    }

    @Override
    public TicketEntity guardarTicket(TicketEntity ticket) {
        return ticketrepository.save(ticket);
    }
    @Override
    public Optional<TicketEntity> getTicketById(Integer id) {
        return ticketrepository.findById(id);
    }

    public TicketEntity guardarTicketVendido(Integer idEvento, Integer idUsuario, Double precio) {
        Optional<EventoEntity> eventoOpt = eventoRepository.findById(idEvento);
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(idUsuario);

        if (!eventoOpt.isPresent() || !usuarioOpt.isPresent()) {
            throw new RuntimeException("Evento o usuario no encontrados.");
        }

        TicketEntity nuevoTicket = new TicketEntity();
        nuevoTicket.setEvento(eventoOpt.get());
        nuevoTicket.setUsuario(usuarioOpt.get());
        nuevoTicket.setPrecio(precio);

        nuevoTicket.setEstado(Enum.VENDIDO);

        return ticketrepository.save(nuevoTicket);
    }


}

