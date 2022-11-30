package com.example.block6personcontrollers;

import java.util.ArrayList;
import java.util.List;

public interface ServicioInterface {

    //Métodos de la interface

    //Crea y devuelve un Objeto de tipo persona, recibe sus atributos
    Persona crearPersona(String nombre, String poblacion, int edad);

    //Devuelve un Objeto de tipo persona con la edad multiplicada por dos
    Persona getPersonaConDobleEdad();

    //Recibe un Objeto de tipo ciudad para añadirlo en una lista
    void addCiudad(Ciudad ciudad);

    //Devuelve una lista de tipo ciudad
    List<Ciudad> getListaCiudades();

}
