package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.DevBusters.gestion_eventos.Entity.Usuarios;
import org.DevBusters.gestion_eventos.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService implements IUsuariosService{

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public List<UsuarioEntity> listaUsuarios() {
        List<UsuarioEntity> listaUsuarios = usuariosRepository.findAll();
        return listaUsuarios;
    }

    @Override
    public UsuarioEntity buscarPorId(Integer id) {
        UsuarioEntity usuarios = usuariosRepository.findById(id).orElse(null);
        return usuarios;
    }

    @Override
    public void guardarUsuarios(UsuarioEntity usuarios) {
        usuariosRepository.save(usuarios);
    }

    @Override
    public void eliminarUsuarios(UsuarioEntity usuarios) {
        usuariosRepository.delete(usuarios);
    }
}
