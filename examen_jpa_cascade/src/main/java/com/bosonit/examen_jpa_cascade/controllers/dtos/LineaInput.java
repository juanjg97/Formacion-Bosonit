package com.bosonit.examen_jpa_cascade.controllers.dtos;

import jakarta.persistence.Entity;
import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LineaInput {
    int id_factura;
    String nombre_producto;
    double cantidad;
    double precio;
}
