package org.DevBusters.gestion_eventos.Controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.DevBusters.gestion_eventos.Entity.UsuarioEntity;
import org.DevBusters.gestion_eventos.Service.UsuarioService;
import org.DevBusters.gestion_eventos.Service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("usuarioController")
@Data
@ViewScoped
public class UsuarioController implements Serializable {

    @Autowired
    private UsuariosService usuariosService;
    private List<UsuarioEntity> usuarios;

    @PostConstruct
    public void init(){
        listaUsuarios();
    }

    public void listaUsuarios(){
        this.usuarios = this.usuariosService.listaUsuarios();
    }
}
