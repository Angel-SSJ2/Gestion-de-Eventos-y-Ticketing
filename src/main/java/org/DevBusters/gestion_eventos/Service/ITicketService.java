package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.entidad.TicketEntity;

import java.util.List;

public interface ITicketService {

    TicketEntity crearTicket(TicketEntity ticket);

    Optional<TicketEntity> buscarPorId(Integer id);

    List<TicketEntity> buscarTodos();

    void eliminarTicket(Integer id);
}
