package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByNombreUsuario(String nombreUsuario);

    Optional<Usuarios> findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);

    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByCorreo(String correo);
}