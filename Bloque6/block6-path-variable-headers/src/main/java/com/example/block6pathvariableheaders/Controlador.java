package com.example.block6pathvariableheaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
/*
* - Petición POST: mandando un objeto JSON en el body
* y recibiendo ese mismo objeto JSON en la respuesta (en el body).
*
* - Petición GET: mandando parámetros en el path (http://localhost:8080/user/{id})
* y devolver el mismo id mandado
*
- Petición PUT: mandando  Request Params
* (http://localhost:8080/post?var1=1&var2=2) devolver un HashMap con los datos mandados . Por ejemplo: [ {var1: 1}, {var2: 2} ]
* */

//Creamos un controlador para manejar las peticiones web
@RestController
public class Controlador {
    //Inyectamos el componente 1 que tiene los métodos para trabajar con la clase persona
    @Autowired
    ComponenteInterfaz componente1;

    //Peticiones solicitadas
    @PostMapping("/user")
    public Persona getPersona(@RequestBody Persona info){
        //Método addPersona del componente 1 usando la clase Persona
        return componente1.getPersona(info);
    }
    @GetMapping("/user/{id}")
    public int getId(@PathVariable int id){
        return id;
    }

    @PutMapping("/post")
    public HashMap <String,String> getVar(@RequestParam(name= "v1") String var1,
                                          @RequestParam(name= "v2") String var2){
        //Método getClaveValor del componente 1 usando la clase Persona
        return componente1.getClaveValor(var1, var2);
    }
}
