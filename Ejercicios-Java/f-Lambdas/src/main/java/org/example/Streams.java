package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Personas{
    private String nombre;
    private int edad;

    public Personas(String nombre, int edad) {
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
        return "Persona selecta {" + "nombre='" + nombre + '\'' + '}';
    }
}

public class Streams {

    static List<Personas>  filtro(List<Personas> grupo_personas){
        List<Personas> personas_selectas = new ArrayList<>();
        int contador=0;
        for (Personas persona : grupo_personas){
            if(persona.getEdad()>18){
                personas_selectas.add(persona);
                contador++;
            }if (contador==3){
                break;
            }
        }
        return personas_selectas;
    }

    public static void main(String[] args) {
        List<Personas> equipo_oficina = Arrays.asList(
            new Personas("Chuchi",50),
            new Personas("Juan",7),
            new Personas("Oscar",5),
            new Personas("Andres",15),
            new Personas("Karla",23),
            new Personas("María",43),
            new Personas("Inés",28)

        ) ;

        List<Personas> resultado1 = new ArrayList<>();
        List<Personas> resultado2 = new ArrayList<>();

        //Sin usar streams
        resultado1=filtro(equipo_oficina);

        //Usando streams con lambdas
        resultado2 = equipo_oficina.stream().filter(persona->persona.getEdad()>18).limit(3).collect(Collectors.toList());

        //Resultados
        System.out.println("Personas selectas sin usar stream: "+resultado1);
        System.out.println("Personas selectas usando stream: "+resultado2);

    }
}
