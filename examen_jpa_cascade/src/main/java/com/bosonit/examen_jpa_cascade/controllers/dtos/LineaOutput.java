package com.bosonit.examen_jpa_cascade.controllers.dtos;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class LineaOutput {
    int id_linea;
    String nombre_producto;
    double cantidad;
    double precio;
}
