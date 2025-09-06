package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.entidad.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITicketRepository extends JpaRepository<TicketEntity, Integer> {

    // Método para encontrar un ticket por el ID del evento
    Optional<TicketEntity> findByIdEvento(Integer idEvento);

    // Método para encontrar un ticket por el ID del usuario
    Optional<TicketEntity> findByIdUsuario(Integer idUsuario);
}
