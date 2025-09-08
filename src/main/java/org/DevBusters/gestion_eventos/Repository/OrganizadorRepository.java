package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.OrganizadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizadorRepository extends JpaRepository<OrganizadorEntity, Integer> {

    Optional<OrganizadorEntity> findByNombreOrganizadorAndContrasena(String nombreOrganizador, String contrasena);
    Optional<OrganizadorEntity> findByNombreOrganizador(String nombreOrganizador);
}
