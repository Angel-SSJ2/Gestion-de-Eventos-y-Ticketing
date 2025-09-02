package org.DevBusters.gestion_eventos.service;

import org.DevBusters.gestion_eventos.entity.Usuarios;

import java.util.List;

public interface IUsuariosService {
    public List<Usuarios> listaUsuarios();
    public Usuarios buscarPorId(Integer id);
    public void guardarUsuarios(Usuarios usuarios);
    public void eliminarUsuarios(Usuarios usuarios);
}
