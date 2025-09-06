package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Enum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.DevBusters.gestion_eventos.Repository.UsuarioRepository;
import org.DevBusters.gestion_eventos.Repository.EventoRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    List<TicketEntity> findByUsuario_IdUsuario(Integer idUsuario);
    Long countByEvento_IdEventoAndEstado(Integer idEvento, Enum estado);

    Optional<TicketEntity> findByCodigoQr(String codigoQr);
    Optional<TicketEntity> findByidTickets(Integer codigoQr);

}