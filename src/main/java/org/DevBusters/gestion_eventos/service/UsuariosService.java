package org.DevBusters.gestion_eventos.Service;

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
    public List<Usuarios> listaUsuarios() {
        List<Usuarios> listaUsuarios = usuariosRepository.findAll();
        return listaUsuarios;
    }

    @Override
    public Usuarios buscarPorId(Integer id) {
        Usuarios usuarios = usuariosRepository.findById(id).orElse(null);
        return null;
    }

    @Override
    public void guardarUsuarios(Usuarios usuarios) {
        usuariosRepository.save(usuarios);
    }

    @Override
    public void eliminarUsuarios(Usuarios usuarios) {
        usuariosRepository.delete(usuarios);
    }
}
