package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;

import java.util.List;

public interface IUsuariosService {
    public List<UsuarioEntity> listaUsuarios();
    public UsuarioEntity buscarPorId(Integer id);
    public void guardarUsuarios(UsuarioEntity usuarios);
    public void eliminarUsuarios(UsuarioEntity usuarios);
}
