package org.DevBusters.gestion_eventos.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.DevBusters.gestion_eventos.Enum;

@Entity
@Table(name = "Tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTickets;

    @ManyToOne
    @JoinColumn(name = "idEvento", nullable = false)
    private EventoEntity evento;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private UsuarioEntity usuario;

    private String codigoQr;

    private Double precio;

    @Enumerated(EnumType.STRING)
    private Enum estado;


}
