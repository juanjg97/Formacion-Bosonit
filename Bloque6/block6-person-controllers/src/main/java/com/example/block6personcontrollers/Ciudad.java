package com.example.block6personcontrollers;

/*
*Al arrancar el programa se debe crear una lista de objetos de tipo Ciudad,
* tendrá los atributos nombre (String) y número de habitantes (int)
* */
public class Ciudad {
    //Atributos
    private String nombre;
    private int numeroHabitantes;


    //Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getNumeroHabitantes() {
        return numeroHabitantes;
    }
    public void setNumeroHabitantes(int numeroHabitantes) {
        this.numeroHabitantes = numeroHabitantes;
    }
}
