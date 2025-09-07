package org.DevBusters.gestion_eventos.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.DevBusters.gestion_eventos.Entity.EventoEntity;
import org.DevBusters.gestion_eventos.Service.EventoService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("eventoController")
@Data
@ViewScoped
public class EventoController implements Serializable {

    @Autowired
    private EventoService eventoService;
    private List<EventoEntity> listaEventos;
    private EventoEntity evento;

    @PostConstruct
    public void init(){
        cargarEventos();
    }

    public void agregarEvento(){
        this.evento = new EventoEntity();
    }

    public void cargarEventos(){
        this.listaEventos = this.eventoService.listaEventos();
    }

    public void guardarEvento(){
        if (this.evento.getIdEvento() == null){
            this.eventoService.guardarEvento(this.evento);
            this.listaEventos.add(this.evento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento agregado con exito"));
        } else {
            this.eventoService.guardarEvento(evento);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento actualizado"));
        }
        PrimeFaces.current().executeScript("PF('ventanaModalEventos').hide");
        PrimeFaces.current().ajax().update("formulario-eventos:mensaje-emergente","formulario-eventos:tabla-eventos");
        this.evento = null;
    }

    public void eliminarEvento(){
        this.eventoService.eliminarEvento(evento);
        this.listaEventos.remove(evento);
        this.evento = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento eliminado con exito"));
        PrimeFaces.current().ajax().update("formulario-eventos:mensaje-emergente","formulario-eventos:tabla-eventos");
    }


}
