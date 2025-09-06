package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.entity.Usuarios;
import org.DevBusters.gestion_eventos.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuarios autenticar(String nombreUsuario, String contrasena) {
        Optional<Usuarios> usuario = usuarioRepository.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
        return usuario.orElse(null);
    }

    @Override
    public Usuarios buscarPorUsername(String username) {
        Optional<Usuarios> usuario = usuarioRepository.findByNombreUsuario(username);
        return usuario.orElse(null);
    }

    @Override
    public Usuarios crearUsuario(Usuarios usuario) {
        // Validar que no exista el username
        if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
            throw new RuntimeException("El username ya existe");
        }

        // Validar que no exista el email
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El email ya está registrado");
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean existeUsername(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    public boolean existeEmail(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
}