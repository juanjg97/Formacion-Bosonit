package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Componente4 implements Componente4Interface{

    private String nombre = "Mi nombre es clase 4";

    public Componente4() {
        System.out.println("Constructor clase 4");
    }

    @Override
    public void despedirse() {
        System.out.println("Hasta luego desde la clase Componente 4");
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
