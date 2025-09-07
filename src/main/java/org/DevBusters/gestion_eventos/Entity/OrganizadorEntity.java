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
    @Column(name = "idOrganizador")
    private Integer idOrganizador;
    @Column(name = "nombreOrganizador")
    private String nombreOrganizador;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contrasena")
    private String contrasena;

}
