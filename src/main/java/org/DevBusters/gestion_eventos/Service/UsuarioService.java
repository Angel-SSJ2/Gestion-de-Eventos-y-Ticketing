package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.DevBusters.gestion_eventos.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Autenticación de usuario por nombreUsuario y contraseña
    @Override
    public UsuarioEntity autenticar(String nombreUsuario, String contrasena) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
        return usuario.orElse(null);
    }

    @Override
    public Optional<UsuarioEntity> buscarPornombreUsuario(String nombreUsuario) {
        return Optional.empty();
    }

    // Buscar usuario por ID
    @Override
    public Optional<UsuarioEntity> buscarUsuarioPorId(Integer idCliente) {
        return usuarioRepository.findById(idCliente);
    }

    // Crear un nuevo usuario validando que no exista nombre de usuario ni correo
    @Override
    public UsuarioEntity crearUsuario(UsuarioEntity usuario) {
        if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean existenombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    public boolean existeCorreo(String correo) {
        return false;
    }


    // Validar existencia de correo
    @Override
    public boolean existeEmail(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    // Listar todos los usuarios
    @Override
    public List<UsuarioEntity> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Eliminar usuario por ID
    @Override
    public void eliminarUsuario(Integer idCliente) {
        usuarioRepository.deleteById(idCliente);
    }
}
