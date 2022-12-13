package com.bosonit.examen_jpa_cascade.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Linea {

    //Llave primaria
    @Id
    @GeneratedValue
    @Column(name="id_linea")
    int id_linea;

    //Columnas
    @Column(name="nombre_producto",nullable = false)
    String nombre_producto;

    @Column(name="cantidad")
    double cantidad;

    @Column(name="precio")
    double precio;

    //Relaciones
    //@JoinColumn => nombre de la nueva columna que tendrá la primary key del objeto factura almacenado.
    @ManyToOne //Debemos poner un OneToMany en factura
    @JoinColumn(name="id_factura")
    Factura factura; //Mismo nombre que en mappedBy y en el setFactura

    public Linea(String nombre_producto, double cantidad, double precio, Factura factura) {
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.factura = factura;
    }
}
