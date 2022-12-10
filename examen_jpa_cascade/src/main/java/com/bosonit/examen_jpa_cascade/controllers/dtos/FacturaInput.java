package com.bosonit.examen_jpa_cascade.controllers.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaInput {
    int id_cliente;
    Double importe_factura;

}
