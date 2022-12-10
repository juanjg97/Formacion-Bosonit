package com.bosonit.examen_jpa_cascade.controllers;

import com.bosonit.examen_jpa_cascade.application.services.FacturaService;
import com.bosonit.examen_jpa_cascade.controllers.dtos.FacturaOutput;
import com.bosonit.examen_jpa_cascade.controllers.dtos.LineaInput;
import com.bosonit.examen_jpa_cascade.domain.entities.Factura;
import com.bosonit.examen_jpa_cascade.domain.entities.Linea;
import com.bosonit.examen_jpa_cascade.mappers.LineaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("factura")
public class Controller {
    @Autowired
    FacturaService facturaService;

    @GetMapping
    public List<FacturaOutput> getAllFactura() {
        return facturaService.getAllFactura().stream().map(Factura::facturaToFacturaOutput).toList();
    }

    @DeleteMapping("/{id_factura}")
    public ResponseEntity<?> deleteFacturaById(@PathVariable int id_factura){
        try {
            facturaService.deleteFacturaByIdFactura(id_factura);
            return ResponseEntity.ok().body("Se borró la factura con el id: " + id_factura);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la factura con el id: " + id_factura);
        }
    }

    @PostMapping("/linea/{id_factura}")
    public ResponseEntity<?> addLineaToFactura(@RequestBody LineaInput lineaInput, @PathVariable int id_factura) {
        try {
            facturaService.getFacturaById(id_factura);
            System.out.println("1 -----------> "+id_factura);
            System.out.println("2 -----------> "+lineaInput);

            facturaService.getFacturaById(lineaInput.getId_factura());

            Linea linea = LineaMapper.mapper.lineaInputToLinea(lineaInput);
            System.out.println("3 -----------> "+linea);

            Factura factura = facturaService.addLineaToFactura(linea, id_factura);
            //System.out.println(factura);


            FacturaOutput facturaOutput = factura.facturaToFacturaOutput();
            return ResponseEntity.ok().body(facturaOutput);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el id de factura");
        }
    }



}
