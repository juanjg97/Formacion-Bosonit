package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaInput;
import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaOutput;
import com.bosonit.springdatavalidation.domain.entities.Asignatura;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutput addAsignatura(AsignaturaInput asignaturaInput) throws Exception;
    AsignaturaOutput getAsignaturaById(int id_asignatura);
    List<AsignaturaOutput> getAllAsignaturas(int pageNumber, int pageSize);
    AsignaturaOutput updateAsignatura(int id_asignatura, AsignaturaInput asignaturaInput);
    void deleteAsignaturaById(int id_asignatura);
}
