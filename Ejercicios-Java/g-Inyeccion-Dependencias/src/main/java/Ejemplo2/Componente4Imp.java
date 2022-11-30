package Ejemplo2;

import org.springframework.stereotype.Component;

@Component
public class Componente4Imp implements Componente4 {
    //Atributos
    private final String nombre = "Mi nombre es clase 4";

    //Constructor
    public Componente4Imp() {
        System.out.println("Constructor Componente4");
    }

    //Métodos sobre escritos
    @Override
    public String despedirse() {return "Hasta luego desde la clase Componente 4";}
    @Override
    public String getNombre() {
        return nombre;
    }
}
