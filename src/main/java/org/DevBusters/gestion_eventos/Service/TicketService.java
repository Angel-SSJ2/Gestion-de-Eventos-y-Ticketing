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
    public Optional<TicketEntity> getTicketById(Integer id) {
        return Optional.empty();
    }

    @Override
    public TicketEntity guardarTicket(TicketEntity ticket) {
        return null;
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

        nuevoTicket.setCodigoQr(UUID.randomUUID().toString());

        nuevoTicket.setEstado(Enum.VENDIDO);

        return ticketrepository.save(nuevoTicket);
    }

    public Map<String, Object> ValidarTicketPorQR(String codigoQR){
        Map<String, Object> resultado = new HashMap<>();
        Optional<TicketEntity> ticketOpt = ticketrepository.findByCodigoQr(codigoQR);
        if (!ticketOpt.isPresent()){
            resultado.put("estado", "Error");
            resultado.put("mensaje", "El oodigo QR no existe");
            return resultado;
        }

        TicketEntity ticket = ticketOpt.get();

        if (ticket.getEstado() == Enum.USADO) {
            resultado.put("estado", "ERROR");
            resultado.put("mensaje", "Este ticket ya ha sido usado.");
            return resultado;
        }

        ticket.setEstado(Enum.VENDIDO);
        ticketrepository.save(ticket);

        resultado.put("estado", "OK");
        resultado.put("mensaje", "Validación exitosa.");
        resultado.put("ticket", ticket);
        return resultado;
    }
}

