package com.bosonit.examen_jpa_cascade.controllers;

import com.bosonit.examen_jpa_cascade.application.services.FacturaService;
import com.bosonit.examen_jpa_cascade.controllers.dtos.FacturaOutput;
import com.bosonit.examen_jpa_cascade.controllers.dtos.LineaInput;
import com.bosonit.examen_jpa_cascade.domain.entities.Factura;
import com.bosonit.examen_jpa_cascade.domain.entities.Linea;
import com.bosonit.examen_jpa_cascade.mappers.FacturaMapper;
import com.bosonit.examen_jpa_cascade.mappers.LineaMapper;
import com.bosonit.examen_jpa_cascade.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

//Etiqueta para indicar que es un controlador y el path principal
@RestController
@RequestMapping("factura")
public class Controller {
    //Inyectamos los servicios para implementar los métodos de la lógica de negocio y CRUD
    @Autowired
    FacturaService facturaService;
    @Autowired
    private FacturaRepository facturaRepository;

    //Aunque no inyectemos un objeto de la interfaz mapper, usaremos la clase para las conversiones

    //Método para obtener información de la BDD y mostrarla en POSTMAN
    @GetMapping
    public List<FacturaOutput> getAllFactura() {

        List<Factura> facturasLista = facturaService.getAllFactura();
        List<FacturaOutput> facturasOutputLista = facturasLista.stream().map(factura -> factura.facturaToFacturaOutput()).toList();
        return facturasOutputLista;

        /* Otra manera de hacerlo.
        return facturaService.getAllFactura() //Regresa todas las facturas
                             .stream()// Permite tratar las colecciones como si de etapas de un proceso ETL, convierte de lista a stream
                             .map(Factura::facturaToFacturaOutput) //Transforma cada uno de los objetos que contiene la colección de entidad a Output facturaToFacturaOutput está definido en la entidad y se puede hacer de esta manera
                             .toList(); //Conviertelo de stream a una lista */
    }

    //Método para eliminar por id, hay que pasar por el path dicho id
    @DeleteMapping("/{id_factura}")
    //Regresa un response entity con la respuesta y en el body la información
    public ResponseEntity<?> deleteFacturaById(@PathVariable int id_factura){
        try {
            //Usa el servicio para eliminar de la bdd (revisa previamente que exista esa factura)
            facturaService.deleteFacturaByIdFactura(id_factura);
            //Retorna un 200
            return ResponseEntity.ok().body("Se borró la factura con el id: " + id_factura);
        } catch (NoSuchElementException e) {
            //Retorna un 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la factura con el id: " + id_factura);
        }
    }

        //Método para agregar una linea a una factura
    @PostMapping("/linea/{id_factura}")
    public ResponseEntity<?> addLineaToFactura(@RequestBody LineaInput lineaInput, @PathVariable int id_factura) {
        try {

            //Doble comprobación con el id de la linea que ingresa en el body y del parámetro en el path
            facturaService.getFacturaById(id_factura); //Revisa que haya una factura con ese id
            facturaService.getFacturaById(lineaInput.getId_factura()); //Revisa que haya una factura con ese id

            //Transforma la lineaInput a linea y asignala a una linea
            Linea linea = LineaMapper.lMapper.lineaInputToLinea(lineaInput);

            // Crea una factura (no nueva) y asignale una linea e indica el id de la factura y guarda en bdd
            Factura factura = facturaService.addLineaToFactura(linea, id_factura);
            //Transforma de entidad a output
            FacturaOutput facturaOutput = factura.facturaToFacturaOutput();

            //Regresa la factura con la linea nueva y una respuesta 200
            return ResponseEntity.ok().body(facturaOutput);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el id de factura");
        }
    }



}
