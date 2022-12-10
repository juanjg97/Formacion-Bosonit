package com.bosonit.examen_jpa_cascade.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Linea {
    @Id
    @GeneratedValue
    int id_linea;

    @ManyToOne
    @JoinColumn(name="id_factura")
    Factura factura;

    @Column(name="nombre_producto",nullable = false)
    String nombre_producto;

    double cantidad;

    double precio;


}
