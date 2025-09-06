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

    @Override
    public UsuarioEntity autenticar(String nombreUsuario, String contrasena) {
        return usuarioRepository.findBynombreUsuarioAndContrasena(nombreUsuario, contrasena);
    }

    @Override
    public Optional<UsuarioEntity> buscarPornombreUsuario(String username) {
        return usuarioRepository.findBynombreUsuario(username);
    }

    @Override
    public Optional<UsuarioEntity> buscarUsuarioPorId(Integer idCliente) {
        return usuarioRepository.findById(idCliente);
    }

    @Override
    public UsuarioEntity crearUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean existenombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsBynombreUsuario(nombreUsuario);
    }

    @Override
    public boolean existeCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    @Override
    public List<UsuarioEntity> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void eliminarUsuario(Integer idCliente) {
        usuarioRepository.deleteById(idCliente);
    }
}