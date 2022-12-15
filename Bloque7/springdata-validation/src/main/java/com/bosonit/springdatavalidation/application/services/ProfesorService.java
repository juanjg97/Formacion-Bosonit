package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.ProfesorInput;
import com.bosonit.springdatavalidation.controllers.dtos.ProfesorOutput;
import com.bosonit.springdatavalidation.domain.entities.Profesor;

import java.util.List;

public interface ProfesorService {
    ProfesorOutput addProfesor(ProfesorInput profesorInput, int id_usuario);
    ProfesorOutput getProfesorById(int id_profesor);
    List<ProfesorOutput> getAllProfesores(int pageNumber, int pageSize);
    ProfesorOutput updateProfesorById(int id_profesor, ProfesorInput profesorInput);
    void deleteProfesorById(int id_profesor);
}
