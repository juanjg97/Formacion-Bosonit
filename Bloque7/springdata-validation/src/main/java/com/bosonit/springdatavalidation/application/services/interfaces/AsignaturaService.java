package com.bosonit.springdatavalidation.application.services.interfaces;

import com.bosonit.springdatavalidation.controllers.dtos.inputs.AsignaturaInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.AsignaturaOutput;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutput addAsignatura(AsignaturaInput asignaturaInput) throws Exception;
    AsignaturaOutput getAsignaturaById(int id_asignatura);
    List<AsignaturaOutput> getAllAsignaturas(int pageNumber, int pageSize);
    AsignaturaOutput updateAsignatura(int id_asignatura, AsignaturaInput asignaturaInput);
    void deleteAsignaturaById(int id_asignatura);
}
