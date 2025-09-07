package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.EventoEntity;
import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.DevBusters.gestion_eventos.Repository.EventoRepository;
import org.DevBusters.gestion_eventos.Repository.TicketRepository;
import org.DevBusters.gestion_eventos.Repository.UsuarioRepository;
import org.DevBusters.gestion_eventos.Enum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService implements ITicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todos los tickets
    @Override
    public List<TicketEntity> listarTicket() {
        return ticketRepository.findAll();
    }

    // Guardar un ticket genérico
    @Override
    public TicketEntity guardarTicket(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }

    // Buscar ticket por ID
    @Override
    public Optional<TicketEntity> getTicketById(Integer id) {
        return ticketRepository.findById(id);
    }

    // Método adicional para eliminar ticket
    @Override
    public void eliminarTicket(Integer id) {
        ticketRepository.deleteById(id);
    }

    // Método específico para guardar un ticket vendido con evento y usuario
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

        return ticketRepository.save(nuevoTicket);
    }

    // Método placeholder para validar QR
    @Override
    public Map<String, Object> validarTicketPorQr(String codigoQr) {
        return Map.of();
    }
}
