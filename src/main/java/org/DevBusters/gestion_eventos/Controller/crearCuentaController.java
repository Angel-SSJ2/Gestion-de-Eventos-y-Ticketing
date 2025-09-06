package org.DevBusters.gestion_eventos.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.DevBusters.gestion_eventos.Service.UsuariosService;
import org.DevBusters.gestion_eventos.Entity.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("crearCuentaController")
@ViewScoped
@Data
public class crearCuentaController implements Serializable {

    @Autowired
    private UsuariosService usuarioService;
    private Usuarios usuario;

    @PostConstruct
    public void init(){
        this.usuario = new Usuarios();
    }

    public void crearCuenta(){
        if (this.usuario.getIdUsuario()==null){
            this.usuarioService.guardarUsuarios(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente agregado con exito"));
        }
    }
}
