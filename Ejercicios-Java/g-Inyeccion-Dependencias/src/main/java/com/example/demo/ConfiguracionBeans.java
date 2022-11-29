package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionBeans {
    public ConfiguracionBeans() {
        System.out.println("Constructor configuración Beans");
    }

    @Bean
    ComponenteB3 getComponenteB3(){
        return new ComponenteB3();
    }

    @Bean
    @Qualifier("Componente1Bean")
    Componente1 getComponente1(){
        var c = new Componente1();
        c.setNombre("Componente1 @Bean");
        return c;
    }
}

