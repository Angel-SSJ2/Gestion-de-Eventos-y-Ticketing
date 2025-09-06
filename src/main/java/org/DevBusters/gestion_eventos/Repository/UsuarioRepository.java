package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findBynombreUsuario(String nombreUsuario);

    UsuarioEntity findBynombreUsuarioAndContrasena(String nombreUsuario, String contrasena);

    boolean existsBynombreUsuario(String nombreUsuario);

    boolean existsByCorreo(String correo);
}