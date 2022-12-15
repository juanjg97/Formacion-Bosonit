package com.bosonit.springdatavalidation.repositories;

import com.bosonit.springdatavalidation.domain.entities.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignaturaRepositorio extends JpaRepository<Asignatura,Integer> {
    //Método personalizado findBy<Columna>

}
