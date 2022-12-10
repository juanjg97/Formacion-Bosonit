package com.bosonit.examen_jpa_cascade.application.services;

import com.bosonit.examen_jpa_cascade.domain.entities.Cliente;
import com.bosonit.examen_jpa_cascade.domain.entities.Factura;
import com.bosonit.examen_jpa_cascade.domain.entities.Linea;
import com.bosonit.examen_jpa_cascade.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImp implements  FacturaService{

    @Autowired
    FacturaRepository facturaRepository;

    @Autowired
    ClienteServiceImp clienteService;

    @Override
    public Factura addFactura(Factura factura, int id_cliente) {
        Cliente cliente = new Cliente();
        cliente = clienteService.getClienteById(id_cliente);
        factura.setCliente(cliente);
        return facturaRepository.save(factura);
    }

    @Override
    public Factura getFacturaById(int id_factura) {
        return facturaRepository.findById(id_factura).orElseThrow();
    }

    @Override
    public List<Factura> getAllFactura() {
        return facturaRepository.findAll();
    }

    @Override
    public void deleteFacturaByIdFactura(int id_factura) {
        getFacturaById(id_factura);
        facturaRepository.deleteById(id_factura);
    }

    @Override
    public Factura addLineaToFactura(Linea linea, int id_factura) {
        Factura factura = getFacturaById(id_factura);
        linea.setFactura(factura);
        List<Linea> lineaList = factura.getLineasFactura();
        lineaList.add(linea);
        factura.setLineasFactura(lineaList);

        return facturaRepository.save(factura);
    }
}
