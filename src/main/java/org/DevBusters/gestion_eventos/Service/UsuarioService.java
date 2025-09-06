package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.Usuarios;
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
        Optional<Usuarios> usuario = usuarioRepository.findByUsernameAndContraseña(nombreUsuario, contrasena);
        return usuario.orElse(null);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        return usuario.orElse(null);
    }

    @Override
    public Usuarios crearUsuario(Usuarios usuario) {
        return null;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        // Validar que no exista el username
        if (usuarioRepository.existsByUsername(usuario.getnombreUsuario())) {
            throw new RuntimeException("El username ya existe");
        }

        // Validar que no exista el email
        if (usuarioRepository.existsByEmail(usuario.getcorreo())) {
            throw new RuntimeException("El email ya está registrado");
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean existeUsername(String nombreUsuario) {
        return usuarioRepository.existsByUsername(nombreUsuario);
    }

    @Override
    public boolean existeEmail(String correo) {
        return usuarioRepository.existsByEmail(correo);
    }
}