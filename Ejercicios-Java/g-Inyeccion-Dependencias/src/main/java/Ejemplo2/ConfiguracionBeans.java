package Ejemplo2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionBeans {

    //Función que devuelve un objeto de la clase Componente 3, es instanciado como un bean para que Springboot guarde el objeto en su entorno
    //La clase componente 3 no tiene el @Component
    @Bean
    Componente3 getComponente3(){
        return new Componente3();
    }

    //Función que modifica un atributo de un objeto de la clase Componente 1 y lo devuelve
    @Bean
    @Qualifier("BeanComponente1")
    Componente1 getComponente1(){
        System.out.println("Se ejecutó el método de bean getComponente1");
        Componente1 componente_a = new Componente1();
        componente_a.setNombre("Componente1 de la clase Configurations");
        return componente_a;
    }
}
