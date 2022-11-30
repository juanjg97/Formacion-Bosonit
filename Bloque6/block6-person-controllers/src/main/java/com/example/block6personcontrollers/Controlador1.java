package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
* Crear un controlador 1 con una URL: /controlador1/addPersona,
* de tipo GET, en los headers tiene que recibir nombre, población y edad.
*
* Utilizando una clase servicio, esta clase creara un objeto Persona,
* la llamada devolverá el objeto persona creado.
*
*En controlador1, en la URL /controlador1/addCiudad,
* petición tipo POST, se añadirá una ciudad a la lista.
 * */

//Clase de tipo controlador y definimos un endpoint general
@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    //Utilizamos Autowired para hacer uso de el objeto persona y los métodos de servicio
    @Autowired
    Servicio servicio;

    //Petición tipo GET
    @GetMapping("/addPersona")
    public Persona addPersona(@RequestHeader("nombre") String nombre,
                              @RequestHeader("poblacion") String poblacion,
                              @RequestHeader("edad") int edad){
        System.out.println("Persona agredada");
        return servicio.crearPersona(nombre, poblacion, edad);

    }

    //Petición tipo POST
    @PostMapping("/addCiudad")
    public void addCiudad(@RequestBody Ciudad ciudad){
        System.out.println("Ciudad agregada");
        servicio.addCiudad(ciudad);
    }
}
