package com.bosonit.springdatavalidation.repositories;

import com.bosonit.springdatavalidation.domain.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonaRepositorio extends JpaRepository <Persona,Integer> {
    //Método personalizado findBy<Columna>

    Persona findByUsuario(String usuario);
}
