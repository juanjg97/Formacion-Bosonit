package com.example.demo;
//Es importante que para la inyección de dependencias quien cree los objetos sea spring

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador1 {

    //Inyectar dependencias sin Autowired
    @Autowired
         //@Qualifier("Componente1Bean")
        @Qualifier("Componente1Component")
    Componente1 c1_2;
    @Autowired
    Componente2 c2;
    @Autowired
    ComponenteB3 c3;

    //----Sin Autowired
    Componente1 c1;

    Componente4Interface c4;

    //Si no usamos Autowired agregamos el objeto en el constructor de la clase donde inyectamos las dependencias
    public Controlador1(@Qualifier("Componente1Component")Componente1 c1, Componente4Interface c4) {
        this.c1=c1;
        this.c4=c4;
        System.out.println("---Constructor de Controlador1");
        System.out.println("---Componente cargado: "+c1.getNombre());
        System.out.println("-- Componente cargado: "+c4.getNombre());
    }


    @GetMapping("/")
    public String saludar(){
        return "Hola desde el RestController1";
    }

    //Utilizamos el bean c1 de la clase Controlador1
    @GetMapping("/c1")
    public String saludarc1(){
        return "Hola usando Restcontroller " + c1.saludar() ;
    }

    @GetMapping("/c1_2")
    public String saludarc1_2(){
        return "Hola usando Restcontroller " + c1_2.saludar() + "sin Autowired" ;
    }

    @GetMapping("/c2")
    public String saludarc2(){
        return "Hola usando Restcontroller " + c2.saludar() ;
    }

    @GetMapping("/c3")
    public String saludarc3(){
        return "Hola usando Restcontroller " + c3.saludar() +"Usando Beans" ;
    }
}
