package com.example.demo;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class Saludo {
    //Función get para obtener recurso de la web
    @GetMapping(value = "/{nombre}" )
    public String saludo (@PathVariable String nombre) {
        return "Hola " + nombre;
    }

    @PostMapping(value = "/useradd" )
    public Persona agregarPersona (@RequestBody Persona persona) {
        persona.setEdad(persona.getEdad()+ 1);
        return persona;
    }
}
