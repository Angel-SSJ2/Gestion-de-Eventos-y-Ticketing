package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.TicketEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITicketService {

    // --- Métodos CRUD básicos ---
    TicketEntity crearTicket(TicketEntity ticket);

    Optional<TicketEntity> buscarPorId(Integer id);

    List<TicketEntity> buscarTodos();

    void eliminarTicket(Integer id);

    TicketEntity guardarTicket(TicketEntity ticket);

    TicketEntity guardarTicketVendido(Integer idEvento, Integer idUsuario, Double precio);
}
