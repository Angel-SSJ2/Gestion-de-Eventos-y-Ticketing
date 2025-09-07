package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<EventoEntity, Integer> {
}
