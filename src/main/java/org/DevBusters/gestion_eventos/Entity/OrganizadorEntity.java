package org.DevBusters.gestion_eventos.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Organizadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrganizador;

    private String nombreOrganizador;

    private String correo;

    private String contrasena;

    @OneToMany(mappedBy = "organizador")
    private List<EventoEntity> eventos;
}
