package org.DevBusters.gestion_eventos.repository;

import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<UsuarioEntity, Integer> {}
