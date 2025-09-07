package org.DevBusters.gestion_eventos.Service;

import org.DevBusters.gestion_eventos.Entity.EventoEntity;
import org.DevBusters.gestion_eventos.Repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService implements IEventoService{

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<EventoEntity> listaEventos() {
        List<EventoEntity> listaEventos = eventoRepository.findAll();
        return listaEventos;
    }

    @Override
    public void guardarEvento(EventoEntity evento) {
        eventoRepository.save(evento);
    }

    @Override
    public void eliminarEvento(EventoEntity evento) {
        eventoRepository.delete(evento);
    }

    @Override
    public EventoEntity buscarEventoPorId(Integer id) {
        EventoEntity evento = eventoRepository.findById(id).orElse(null);
        return evento;
    }
}
