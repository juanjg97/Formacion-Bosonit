package com.example.block6pathvariableheaders;

import org.springframework.stereotype.Component;

import java.util.HashMap;

//Hacemos una clase componente (para inyectarla después) que implementa una interfaz por motivos de código limpio
@Component
public class ComponenteImp implements ComponenteInterfaz {

    //Hacemos una colección de tipo MAP para guardar clave y valor
    HashMap<String,String > diccionario = new HashMap<String, String >();

    //Sobreescribimos los métodos de la interfaz

    //Método que recibe a una persona (atributos) y devuelve a la misma persona
    @Override
    public Persona getPersona(Persona persona) {
        return persona;
    }
    //Método que recibe y devuelve, la clave y valor de un hashmap
    @Override
    public HashMap <String,String> getClaveValor(String v1, String v2) {
        diccionario.put("var1",v1);
        diccionario.put("var2",v2);
        return diccionario;
    }
}
