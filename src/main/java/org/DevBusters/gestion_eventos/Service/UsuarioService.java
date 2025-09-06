package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.entidad.Usuario;
import org.DevBusters.gestion_eventos.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Usuario autenticar(String nombreUsuario, String contrasena) {
        // Corrección: El método debe buscar por nombreUsuario y contrasena
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
        return usuario.orElse(null);
    }

    @Override
    public Usuario buscarPorUsername(String nombreUsuario) {
        // Corrección: El método debe buscar por nombreUsuario
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);
        return usuario.orElse(null);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        // Validar que no exista el nombre de usuario
        if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        // Validar que no exista el correo
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
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