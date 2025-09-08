package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.TicketEntity;
import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {

    Optional<TicketEntity> findByEvento_IdEvento(Integer idEvento);

    Optional<TicketEntity> findByUsuario_IdUsuario(Integer idUsuario);

    List<TicketEntity> findByUsuario(UsuarioEntity usuario);
}

