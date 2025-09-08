package org.DevBusters.gestion_eventos.Repository;

import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    // Método para autenticar por nombre de usuario y contraseña
    Optional<UsuarioEntity> findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);

    // Método para buscar por nombre de usuario
    Optional<UsuarioEntity> findByNombreUsuario(String nombreUsuario);

    // Método para verificar si un nombre de usuario existe
    boolean existsByNombreUsuario(String nombreUsuario);

    // Método para verificar si un correo existe
    boolean existsByCorreo(String correo);
}
