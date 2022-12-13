package com.bosonit.examen_jpa_cascade.application.services;

import com.bosonit.examen_jpa_cascade.domain.entities.Factura;
import com.bosonit.examen_jpa_cascade.domain.entities.Linea;

import java.util.List;

/*
 * Métodos que contendrán la lógica de negocio y la lógica CRUD usando los repositorios
 * Add utilizará .save() del repositorio, get utilizará .findById() del repositorio
 *
 * Devuelven un objeto de la misma clase (la mayoría de la veces)
 * */
public interface FacturaService {
    //Método que construye una factyra existente y la guarda  en la BDD
    Factura addFactura(Factura factura,
                       Double importe_total_factura,
                       List<Linea> lineasLista,
                       int id_cliente);
    //Método para buscar y obtener una factura con cierto id de la BDD
    Factura getFacturaById(int id_factura);
    //Método para obtener todas las facturas con un mismo id de la bdd
    List<Factura> getAllFactura();
    //Método para eliminar una factura con cierto id de la bdd
    void deleteFacturaByIdFactura(int id_factura);
    //Método para agregar una línea a una factura existente con cierto id en la bdd
    Factura addLineaToFactura(Linea linea, int id_factura);
}
