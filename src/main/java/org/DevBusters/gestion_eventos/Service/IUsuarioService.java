package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.Usuarios;

public interface IUsuarioService {
    Usuarios autenticar(String nombreUsuario, String contrasena);

    Usuarios buscarPorUsername(String nombreUsuario);

    Usuarios crearUsuario(Usuarios usuario);

    boolean existeUsername(String nombreUsuario);

    boolean existeEmail(String correo);

    List<Usuarios> listarUsuario();

    public void guardarUsuario(Usuario cliente);

    Usuarios login(String email, String contrasena);

    public Usuarios buscarUsuarioPorId(Integer idCliente);

    public void eliminarUsuario(Usuarios cliente);
}