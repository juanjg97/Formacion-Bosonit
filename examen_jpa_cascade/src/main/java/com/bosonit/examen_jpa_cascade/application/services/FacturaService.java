package com.bosonit.examen_jpa_cascade.application.services;

import com.bosonit.examen_jpa_cascade.domain.entities.Factura;
import com.bosonit.examen_jpa_cascade.domain.entities.Linea;

import java.util.List;

public interface FacturaService {
    Factura addFactura(Factura factura, int id_cliente);
    Factura getFacturaById(int id_factura);
    List<Factura> getAllFactura();
    void deleteFacturaByIdFactura(int id_factura);
    Factura addLineaToFactura(Linea linea, int id_factura);
}
