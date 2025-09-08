package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.OrganizadorEntity;
import org.DevBusters.gestion_eventos.Repository.OrganizadorRepository;
import org.DevBusters.gestion_eventos.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizadorService implements IOrganizadorService{

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<OrganizadorEntity> listaOrganizadores() {
        return organizadorRepository.findAll();
    }

    @Override
    public void eliminarUsuarios(OrganizadorEntity organizador) {
        organizadorRepository.delete(organizador);
    }

    @Override
    public OrganizadorEntity autenticarOrganizador(String nombreOrganizador, String contrasena) {
        Optional<OrganizadorEntity> organizador = organizadorRepository.findByNombreOrganizadorAndContrasena(nombreOrganizador, contrasena);
        return organizador.orElse(null);
    }

    @Override
    public Optional<OrganizadorEntity> buscarPorNombreOrganizador(String nombreOrganizador) {
        return Optional.empty();
    }

    @Override
    public Optional<OrganizadorEntity> buscarOrganizadorPorId(Integer id) {
        return organizadorRepository.findById(id);
    }

    @Override
    public OrganizadorEntity crearOrganizador(OrganizadorEntity organizador) {
        if (usuarioRepository.existsByNombreUsuario(organizador.getNombreOrganizador())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        if (usuarioRepository.existsByCorreo(organizador.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        return organizadorRepository.save(organizador);
    }

    @Override
    public boolean existenombreUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    public boolean existeCorreo(String correo) {
        return false;
    }

    @Override
    public boolean existeEmail(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
}
