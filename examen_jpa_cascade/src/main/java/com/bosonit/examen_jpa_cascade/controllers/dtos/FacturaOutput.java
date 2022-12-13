package com.bosonit.examen_jpa_cascade.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaOutput {
    int id_factura;
    double importe_total_factura;

    ClienteOutput clienteOutput;
    List<LineaOutput> lineaOutputList;

    @Override
    public String toString() {
        return "FacturaOutput{" +
                "id_factura=" + id_factura +
                ", importe_factura=" + importe_total_factura +
                ", clienteOutput=" + clienteOutput +
                ", lineaOutputList=" + lineaOutputList +
                '}';
    }
}
