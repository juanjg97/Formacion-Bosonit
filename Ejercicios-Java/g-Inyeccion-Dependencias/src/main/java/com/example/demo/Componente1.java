package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//Agregamos @Component para indicar que queremos que ésta clase sea un bean
@Component
@Qualifier("Componente1Component")

public class Componente1 {
    //Agregamos una salida para comprobar cuando se construya un objeto
    private String nombre = "Componente1 @Component";
    public Componente1() {
        System.out.println("Constructor Componente1");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String saludar(){
        return "Hola, soy un componente de la clase Componente1, mi nombre es: "+getNombre();
    }
}
