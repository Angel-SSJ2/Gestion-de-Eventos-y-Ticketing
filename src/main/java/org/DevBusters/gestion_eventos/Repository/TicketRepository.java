package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Enum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    List<TicketEntity> findByUsuario_IdUsuario(Integer idUsuario);

    Long countByEvento_IdEventoAndEstado(Integer idEvento, Enum estado);

}
