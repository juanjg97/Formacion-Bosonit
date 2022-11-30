package com.example.block6personcontrollers;
/*
Crear 3 objetos Persona diferentes con funciones que tengan la etiqueta @Bean. 
La primera función pondrá el nombre a ‘bean1’, el segundo a “bean2” y el tercero a “bean3”.
Usar @Qualifiers
* */
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

//Usamos la anotación configuration para utilizar la etiqueta @Beans
@Configuration
public class ConfiguracionBeans {

    //Creamos los beans y asignamos un Qualifier para que no se repitan
    @Bean
    @Qualifier("listaCiudades")
    List<Ciudad> crearLista(){
        return new ArrayList<>();
    }

    //Creamos las funciones solicitadas
    @Bean
    @Qualifier("bean1")
    Persona personaA() {
        Persona pA = new Persona();
        pA.setNombre("Juanjo");
        pA.setPoblacion("Toluca");
        pA.setEdad(25);
        return pA;
    }

    @Bean
    @Qualifier("bean2")
    Persona personaB() {
        Persona pB = new Persona();
        pB.setNombre("Chuchi");
        pB.setPoblacion("Logroño");
        pB.setEdad(50);
        return pB;
    }

    @Bean
    @Qualifier("bean3")
    Persona personaC() {
        Persona pC = new Persona();
        pC.setNombre("Guille");
        pC.setPoblacion("LaRioja");
        pC.setEdad(28);
        return pC;
    }
}
