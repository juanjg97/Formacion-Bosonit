package com.bosonit.block16ticket.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTicket")
    int idTicket;
    @Column(name = "idCliente")
    int idCliente;
    @Column(name = "nombreCliente")
    String nombreCliente;
    @Column(name = "apellidoCliente")
    String apellidoCliente;
    @Column(name = "emailCliente")
    String emailCliente;
    @Column(name = "tripOrigin")
    String tripOrigin;
    @Column(name = "tripDestination")
    String tripDestination;
    @Column(name = "arrivalDate")
    String arrivalDate;
    @Column(name = "departureDate")
    String departureDate;
}
