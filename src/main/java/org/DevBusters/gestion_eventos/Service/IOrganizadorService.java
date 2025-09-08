package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.OrganizadorEntity;

import java.util.List;
import java.util.Optional;

public interface IOrganizadorService {

    public List<OrganizadorEntity> listaOrganizadores();
    public void eliminarUsuarios(OrganizadorEntity organizador);

    OrganizadorEntity autenticarOrganizador(String nombreOrganizador, String contrasena);
    Optional<OrganizadorEntity> buscarPorNombreOrganizador(String nombreOrganizador);
    Optional<OrganizadorEntity> buscarOrganizadorPorId(Integer id);

    OrganizadorEntity crearOrganizador(OrganizadorEntity organizador);

    boolean existenombreUsuario(String nombreUsuario);
    boolean existeCorreo(String correo);;
    boolean existeEmail(String correo);
}
