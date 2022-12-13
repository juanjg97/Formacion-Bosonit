package com.bosonit.examen_jpa_cascade.application.services;

import com.bosonit.examen_jpa_cascade.domain.entities.Cliente;
import com.bosonit.examen_jpa_cascade.domain.entities.Factura;
import com.bosonit.examen_jpa_cascade.domain.entities.Linea;
import com.bosonit.examen_jpa_cascade.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Implementación del servicio, sobreescribimos los métodos del servicio, después serán usados en el controlador o en donde se requieran.
//Se implementa la lógica de negocio, junto con la CRUD con ayuda del repositorio
@Service
public class FacturaServiceImp implements  FacturaService{

    //Inyectamos el repositorio para la lógica CRUD
    //.save(), findById(),findAll(),deleteById(),
    @Autowired
    FacturaRepository facturaRepository;
    //Inyectamos servicio del cliente para buscar un cliente por su id
    @Autowired
    ClienteServiceImp clienteService;

    //Construimos una factura existente, y buscamos un cliente por su id, para ligar el id de ese cliente (primaryKey)
    //a dicha factura, finalmente la guardamos en la bdd
    @Override
    public Factura addFactura(Factura factura, Double importe_total_factura,
                              List<Linea> lineasLista, int id_cliente ){

        Cliente cliente = clienteService.getClienteById(id_cliente);
        factura.setCliente(cliente);
        factura.setImporte_total_factura(importe_total_factura);
        factura.setLineasFactura(lineasLista);

        return facturaRepository.save(factura);
    }

    //Método para buscar y obtener una factura con cierto id de la bdd
    @Override
    public Factura getFacturaById(int id_factura) {
        return facturaRepository.findById(id_factura).orElseThrow();
    }

    //Método para buscar y obtener todas las facturas de la bdd
    @Override
    public List<Factura> getAllFactura() {
        return facturaRepository.findAll();
    }

    //Método para eliminar una factura con cierto ID de la BDD
    @Override
    public void deleteFacturaByIdFactura(int id_factura) {
        facturaRepository.findById(id_factura).orElseThrow(); //Comprueba que exista
        facturaRepository.deleteById(id_factura); //Eliminala
    }

    //Método para agregar una línea a una factura existente, 1.- Obtenemos la factura por su id
    //2.- Obtenemos las líneas que ya tiene esa factura en una lista nueva, 3.- Ligamos la nueva línea a la factura con el id de la factura (pk)
    //3.- Agregamos la linea nueva a la lista nueva, 4.- Le pasamos a esa factura la lista nueva con la linea nueva agregada
    //5.- Guardamos en la BDD
    @Override
    public Factura addLineaToFactura(Linea linea, int id_factura) {
        Factura factura = getFacturaById(id_factura);
        List<Linea> lineaList = factura.getLineasFactura();

        linea.setFactura(factura);
        lineaList.add(linea);
        factura.setLineasFactura(lineaList);

        return facturaRepository.save(factura);
    }
}
