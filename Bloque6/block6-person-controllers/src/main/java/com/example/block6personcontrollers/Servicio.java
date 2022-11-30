package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

//Indicamos que es un servicio y que la clase implementa a la interfaz ServicioInterface
@Service
public class Servicio implements ServicioInterface{

    // Implementa Autowired para utilizar los métodos de la clase ciudad
    @Autowired
    List<Ciudad> listaCiudades;

    //----------------------------------------------------Código para la clase Persona

    //Crea un objeto de tipo persona
    public Persona persona1 = new Persona();

    //Sobreescribe los métodos de la interfaz ServicioInterface

    //Asigna valores a los atributos de la persona1 con los getters y setters
    @Override
    public Persona crearPersona(String nombre, String poblacion, int edad) {
        persona1.setNombre(nombre);
        persona1.setPoblacion(poblacion);
        persona1.setEdad(edad);
        return persona1;
    }

    //Utiliza un getter para obtener la edad y multiplicarla por dos y un setter para asignarla
    @Override
    public Persona getPersonaConDobleEdad() {
        persona1.setEdad((persona1.getEdad() * 2));
        return persona1;
    }

    //----------------------------------------------------Código para la clase ciudad
    //Utiliza un método de las listas para agregar a la listaCiudades una ciudad
    @Override
    public void addCiudad(Ciudad ciudad) {
        listaCiudades.add(ciudad);
    }
    //Devuelve la lista ciudades
    @Override
    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }
}
