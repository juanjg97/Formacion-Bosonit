package com.bosonit.examen_jpa_cascade.controllers.dtos;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class LineaInput {
    int id_factura;
    String nombre_producto;
    double cantidad;
    double precio;
}
