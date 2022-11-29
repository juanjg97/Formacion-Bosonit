package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Alumnos{
    private String nombre;
    private int edad;

    public Alumnos(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

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

    @Override
    public String toString() {
        return "Alumnos{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}

public class StreamMap {
    public static void main(String[] args) {
        List<Alumnos> alumnos = Arrays.asList(new Alumnos("Juan",12), new Alumnos("María",22),new Alumnos("Carlos",33));

        //Recuperar los nombres y edades de una lista de objetos, recolectarlos y ponerlos en una lista
        List <String> nombres = alumnos.stream().map(alumno->alumno.getNombre()).collect(Collectors.toList());
        List <Integer> edades = alumnos.stream().map(alumno->alumno.getEdad()).collect(Collectors.toList());

        System.out.println(nombres);
        System.out.println(edades);

        //Crear una lista de objetos usando dos listas, una de strings y una de integers
        //List <Alumnos> alumnos2 = nombres.stream().map(nombre-> new Alumnos(nombre)).collect(Collectors.toList());
    }

}
