
package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Enum;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITicketService {

    public List<TicketEntity> listarTicket();

    Map<String, Object> validarTicketPorQr(String codigoQr);

    Optional<TicketEntity> getTicketById(Integer id);

    TicketEntity guardarTicket(TicketEntity ticket);

    TicketEntity guardarTicketVendido(Integer idEvento, Integer idUsuario, Double precio);
}