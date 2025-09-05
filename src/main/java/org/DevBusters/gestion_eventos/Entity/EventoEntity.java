package org.DevBusters.gestion_eventos.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Eventos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @ManyToOne
    @JoinColumn(name = "idOrganizador", nullable = false)
    private OrganizadorEntity organizador;

    private String nombreEvento;

    private String descripcion;

    private Date fechaEvento;

    private String ubicacion;

    @OneToMany(mappedBy = "evento")
    private List<TicketEntity> tickets;
}
