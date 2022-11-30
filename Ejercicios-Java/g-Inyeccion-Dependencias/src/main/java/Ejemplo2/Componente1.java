package Ejemplo2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ClaseComponente1")
public class Componente1 {
    private String nombre = "Componente1 de la clase componente1";
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
        return "Hola, soy "+getNombre();
    }
}
