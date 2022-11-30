package com.example.block6personcontrollers;

import org.springframework.stereotype.Component;

@Component
public class Persona {
    //Atributos solicitados
    private String nombre;
    private int edad;
    private String poblacion;

    //Getters y setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getPoblacion() {
        return poblacion;
    }
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
}
