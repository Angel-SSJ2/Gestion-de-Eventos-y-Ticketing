package org.DevBusters.gestion_eventos.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contrasena")
    private String contrasena;

    @OneToMany(mappedBy = "usuario")
    private List<TicketEntity> tickets;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }
}
