package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.OrganizadorEntity;
import org.DevBusters.gestion_eventos.Repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrganizadorService implements IOrganizadorService{

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Override
    public List<OrganizadorEntity> listaOrganizadores() {
        List<OrganizadorEntity> listaOrganizadores = organizadorRepository.findAll();
        return  listaOrganizadores;
    }

    @Override
    public OrganizadorEntity buscarPorId(Integer id) {
        OrganizadorEntity organizador = organizadorRepository.findById(id).orElse(null);
        return organizador;
    }

    @Override
    public void guardarUsuarios(OrganizadorEntity organizador) {
        organizadorRepository.save(organizador);
    }

    @Override
    public void eliminarUsuarios(OrganizadorEntity organizador) {
        organizadorRepository.delete(organizador);
    }
}
