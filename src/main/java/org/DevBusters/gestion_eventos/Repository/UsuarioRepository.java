package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByUsername(String nombreUsuario);

    Optional<Usuarios> findByUsernameAndContraseña(String nombreUsuario, String contrasena);

    boolean existsByUsername(String nombreUsuario);

    boolean existsByEmail(String correo);
}
