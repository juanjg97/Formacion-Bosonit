package Ejemplo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Componente2 {
    //Inyección de dependencias
    @Autowired
    Componente1 componente1;

    @Autowired
    @Qualifier("ClaseComponente1")
    Componente1 componente_aa;
    //-----------------------------------------------------------
    //Atributos
    private String nombre = "Componente2 de la clase componente2";
    //Constructor
    public Componente2() {
        System.out.println("Constructor Componente2");
    }
    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //Métodos

    public String saludar(){
        return "Hola, soy "+getNombre();
    }
    public String sadoDoble(){return saludar()+componente1.saludar();}

    public void cambiarNombre(){
        componente_aa.setNombre("NombreModificado");
    }


}
