package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.OrganizadorEntity;

import java.util.List;

public interface IOrganizadorService {

    public List<OrganizadorEntity> listaOrganizadores();
    public OrganizadorEntity buscarPorId(Integer id);
    public void guardarUsuarios(OrganizadorEntity organizador);
    public void eliminarUsuarios(OrganizadorEntity organizador);
}
