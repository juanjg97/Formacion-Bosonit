package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaInput;
import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaOutput;
import com.bosonit.springdatavalidation.domain.entities.Asignatura;
import com.bosonit.springdatavalidation.domain.entities.Student;
import com.bosonit.springdatavalidation.exceptions.UnprocessableEntityException;
import com.bosonit.springdatavalidation.repositories.AsignaturaRepositorio;
import com.bosonit.springdatavalidation.repositories.StudentRepositorio;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AsignaturaServiceImp implements AsignaturaService{
    @Autowired
    AsignaturaRepositorio asignaturaRepositorio;
    @Autowired
    StudentRepositorio studentRepositorio;
    @Autowired
    StudentServiceImp studentServiceImp;

    @Override
    public AsignaturaOutput addAsignatura(AsignaturaInput asignaturaInput) {
        return null;
    }

    @Override
    public AsignaturaOutput getAsignaturaById(int id_asignatura) {
        return null;
    }

    @Override
    public List<AsignaturaOutput> getAllAsignaturas(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public AsignaturaOutput updateAsignatura(int id_asignatura, AsignaturaInput asignaturaInput) {
        return null;
    }

    @Override
    public void deleteAsignaturaById(int id_asignatura) {

    }
}
