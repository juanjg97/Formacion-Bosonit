package com.bosonit.tripbackend.controllers;

import com.bosonit.tripbackend.application.services.interfaces.ClienteService;
import com.bosonit.tripbackend.dtos.ClienteInput;
import com.bosonit.tripbackend.dtos.ClienteOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping("/addcliente")
    public ResponseEntity<ClienteOutput> addCliente(@RequestBody ClienteInput clienteInput) throws Exception{
        ClienteOutput pO= clienteService.addCliente(clienteInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(pO);
    }


    @GetMapping("/getall")
    public Iterable<ClienteOutput> getAllClientes(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                              @RequestParam(defaultValue = "4", required = false) int pageSize) {
        System.out.println("Entrando a la función obtener todas las Clientes");
        Iterable<ClienteOutput>  clientesO = clienteService.getAllClientes(pageNumber,pageSize);
        return clientesO;
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable int id)
    {
        ClienteOutput cO = clienteService.getClienteById(id);
        return ResponseEntity.ok().body(cO);
    }


    @PutMapping("/updateCliente")
    public ResponseEntity<ClienteOutput> updateCliente(@RequestBody ClienteInput clienteInput) {
        ClienteOutput cO = clienteService.updateCliente(clienteInput.getIdCliente(),clienteInput);
        return ResponseEntity.ok().body(cO);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ClienteOutput> deleteCliente(@PathVariable int id) {
        ClienteOutput po = clienteService.getClienteById(id);
        clienteService.deleteClienteById(id);
        return ResponseEntity.ok().body(po);
    }
}
