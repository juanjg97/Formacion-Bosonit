package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/*
* Crear un controlador 2 con una URL /controlador2/getPersona,
* debe devolver el objeto Persona recibido en el controlador 1 con la edad multiplicada por dos.
*
*En controlador2, en la URL /controlador1/getCiudad, p
* petición tipo GET, se devolverá la lista de las ciudades existentes.
* */


//Clase de tipo controlador y definimos un endpoint general
@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    //Utilizamos Autowired para hacer uso de el objeto persona y los métodos de servicio
    @Autowired
    Servicio servicio;

    //Utilizando GET devolvemos a la persona que recibimos en controler 1 con su edad multiplicada por dos.
    @GetMapping("/getPersona")
    public Persona getPersona(){
        return servicio.getPersonaConDobleEdad();
    }

    //Utilizando y devolvemos una lista de tipo Ciudad
    @GetMapping("/getCiudad")
    public List<Ciudad> getCiudad(){
        return servicio.getListaCiudades();
    }
}
