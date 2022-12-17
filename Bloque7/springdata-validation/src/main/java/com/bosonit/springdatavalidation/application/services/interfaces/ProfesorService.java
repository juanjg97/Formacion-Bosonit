package com.bosonit.springdatavalidation.application.services.interfaces;

import com.bosonit.springdatavalidation.controllers.dtos.inputs.ProfesorInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.ProfesorOutput;

import java.util.List;

public interface ProfesorService {
    ProfesorOutput addProfesor(ProfesorInput profesorInput, int id_usuario) throws Exception;
    ProfesorOutput getProfesorById(int id_profesor);
    List<ProfesorOutput> getAllProfesores(int pageNumber, int pageSize);
    ProfesorOutput updateProfesorById(int id_profesor, ProfesorInput profesorInput);
    void deleteProfesorById(int id_profesor);
}
