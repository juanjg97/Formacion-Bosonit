package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Componente2 {

    //Inyectamos componente con Autowired

    @Autowired
        //@Qualifier("Componente1Bean")
        @Qualifier("Componente1Component")
    Componente1 c1;

    public Componente2() {
        System.out.println("Constructor Componente2");
    }

    public String saludar(){
        return "Hola, soy un componente de la clase Componente2 "+c1.saludar();
    }
}
