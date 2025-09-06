package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.EventoEntity;
import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Enum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<EventoEntity, Integer>{

    Optional<EventoEntity> findByidEvento(Integer codigoQr);
    //Optional<TicketEntity> findByidTicket(Integer codigoQr);

}
