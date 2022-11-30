package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Crear un controlador con una URL /controlador/bean/{bean} dependiendo del parámetro
  mandado devolver cada uno de los Beans.
/controlador/bean/bean1 devolverá el objeto cuyo nombre sea bean1 y así sucesivamente*/


//Clase de tipo controlador y definimos un endpoint general
@RestController
@RequestMapping("/controlador")
public class Controlador {

    //Utilizamos Autowired para usar los métodos de los beans y Qualifiers para especificar a los que nos referimos
    @Autowired
    @Qualifier("bean1")
    Persona bean1;
    @Autowired
    @Qualifier("bean2")
    Persona bean2;
    @Autowired
    @Qualifier("bean3")
    Persona bean3;

    @GetMapping("/bean/{numeroDeBean}")
    Object getPersonaNumeroDeBean(@PathVariable String numeroDeBean) {
        return switch (numeroDeBean) {
            case "bean1" -> bean1;
            case "bean2" -> bean2;
            case "bean3" -> bean3;
            default -> null;
        };
    }
}
