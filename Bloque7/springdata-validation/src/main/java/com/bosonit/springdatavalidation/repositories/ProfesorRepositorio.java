package com.bosonit.springdatavalidation.repositories;

import com.bosonit.springdatavalidation.domain.entities.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepositorio extends JpaRepository<Profesor,Integer> {
    //Método personalizado findBy<Columna>
}
