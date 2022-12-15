package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.ProfesorInput;
import com.bosonit.springdatavalidation.controllers.dtos.ProfesorOutput;
import com.bosonit.springdatavalidation.domain.entities.Profesor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImp implements ProfesorService{

    @Override
    public ProfesorOutput addProfesor(ProfesorInput profesorInput, int id_usuario) {
        return null;
    }

    @Override
    public ProfesorOutput getProfesorById(int id_profesor) {
        return null;
    }

    @Override
    public List<ProfesorOutput> getAllProfesores(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public ProfesorOutput updateProfesorById(int id_profesor, ProfesorInput profesorInput) {
        return null;
    }

    @Override
    public void deleteProfesorById(int id_profesor) {

    }
}
