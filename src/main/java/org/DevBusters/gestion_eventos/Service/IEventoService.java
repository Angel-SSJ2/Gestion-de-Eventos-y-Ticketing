package org.DevBusters.gestion_eventos.Service;

public interface IEventoService {

    public List<EventoEntity> listaEventos();
    public void guardarEvento(EventoEntity evento);
    public void eliminarEvento(EventoEntity evento);
    public EventoEntity buscarEventoPorId(Integer id);
}
