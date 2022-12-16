package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaInput;
import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaOutput;
import com.bosonit.springdatavalidation.controllers.dtos.StudentInput;
import com.bosonit.springdatavalidation.controllers.dtos.StudentOutput;
import com.bosonit.springdatavalidation.domain.entities.Asignatura;
import com.bosonit.springdatavalidation.domain.entities.Student;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import com.bosonit.springdatavalidation.exceptions.UnprocessableEntityException;
import com.bosonit.springdatavalidation.mappers.AsignaturaMapper;
import com.bosonit.springdatavalidation.mappers.StudentMapper;
import com.bosonit.springdatavalidation.repositories.AsignaturaRepositorio;
import com.bosonit.springdatavalidation.repositories.StudentRepositorio;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Service
public class AsignaturaServiceImp implements AsignaturaService{
    @Autowired
    AsignaturaRepositorio asignaturaRepositorio;
    @Autowired
    StudentRepositorio studentRepositorio;

    @Autowired StudentServiceImp studentServiceImp;

    @Override
    public AsignaturaOutput addAsignatura(AsignaturaInput asignaturaInput) throws Exception {
        checkInformationAsignatura(asignaturaInput);

        try {
            Student student = studentRepositorio.findById(asignaturaInput.getId_student()).orElseThrow();
            Asignatura asignatura = AsignaturaMapper.aMapper.asignaturaInputToAsignatura(asignaturaInput);
            asignatura.setStudent(student);

            List<Asignatura> listaAsignaturas = student.getAsignaturaList();
            listaAsignaturas.add(asignatura);

            student.setId_student(asignaturaInput.getId_student());
            student.setAsignaturaList(listaAsignaturas);

            AsignaturaOutput asignaturaOutput = AsignaturaMapper.aMapper.asignaturaToAsignaturaOutput(asignaturaRepositorio.save(asignatura));

            return asignaturaOutput;

        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró un estudiante con el id: " + asignaturaInput.getId_student());
        }
    }

    @Override
    public AsignaturaOutput getAsignaturaById(int id_asignatura){
        try {
            Asignatura asignatura = asignaturaRepositorio.findById(id_asignatura).orElseThrow();
            AsignaturaOutput asignaturaOutput = AsignaturaMapper.aMapper.asignaturaToAsignaturaOutput(asignatura);

            return asignaturaOutput;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró la asignatura con id: " + id_asignatura);
        }
    }

    @Override
    public List<AsignaturaOutput> getAllAsignaturas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        List <Asignatura> asignaturas = asignaturaRepositorio.findAll(pageRequest).getContent();
        List<AsignaturaOutput> asignaturasO = StreamSupport
                .stream(asignaturas.spliterator(),false)
                .map(asignatura -> AsignaturaMapper.aMapper.asignaturaToAsignaturaOutput(asignatura)).toList();

        return asignaturasO;
    }

    @Override
    public AsignaturaOutput updateAsignatura(int id_asignatura, AsignaturaInput asignaturaInput) {
        try {

            Asignatura oldAsignatura = asignaturaRepositorio.findById(id_asignatura).orElseThrow();
            Asignatura newAsignatura = AsignaturaMapper.aMapper.asignaturaInputToAsignatura(asignaturaInput);

            newAsignatura.setId_asignatura(id_asignatura);
            AsignaturaOutput asignaturaOutput = AsignaturaMapper.aMapper.asignaturaToAsignaturaOutput(asignaturaRepositorio.save(newAsignatura));
            return asignaturaOutput;

        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Asignatura no encontrada");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAsignaturaById(int id_asignatura) {
        try {
            asignaturaRepositorio.findById(id_asignatura).orElseThrow();
            asignaturaRepositorio.deleteById(id_asignatura);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public void checkInformationAsignatura(AsignaturaInput asignaturaInput) throws Exception {
        if (asignaturaInput.getFecha_inicio()== null) {
            throw new UnprocessableEntityException("La fecha inicial no puede ser null");
        }
    }
}
