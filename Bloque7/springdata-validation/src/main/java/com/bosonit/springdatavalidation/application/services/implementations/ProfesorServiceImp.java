package com.bosonit.springdatavalidation.application.services.implementations;

import com.bosonit.springdatavalidation.application.services.interfaces.ProfesorService;
import com.bosonit.springdatavalidation.controllers.dtos.inputs.ProfesorInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.ProfesorOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.StudentOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs_full.ProfesorFullOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.domain.entities.Profesor;
import com.bosonit.springdatavalidation.domain.entities.Student;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import com.bosonit.springdatavalidation.exceptions.UnprocessableEntityException;
import com.bosonit.springdatavalidation.mappers.ProfesorMapper;
import com.bosonit.springdatavalidation.mappers.StudentMapper;
import com.bosonit.springdatavalidation.repositories.PersonaRepositorio;
import com.bosonit.springdatavalidation.repositories.ProfesorRepositorio;
import com.bosonit.springdatavalidation.repositories.StudentRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
public class ProfesorServiceImp implements ProfesorService {
    @Autowired
    ProfesorRepositorio profesorRepositorio;
    @Autowired
    PersonaRepositorio personaRepositorio;
    @Autowired
    StudentRepositorio studentRepositorio;


    @Override
    public ProfesorOutput addProfesor(ProfesorInput profesorInput, int id_usuario) throws Exception {
        checkInformationProfesor(profesorInput);

        try{
            Persona persona = personaRepositorio.findById(id_usuario).orElseThrow();

            if(persona.getProfesor()!=null || persona.getStudent()!=null){
                throw new UnprocessableEntityException("La persona: " + id_usuario + " ya tiene datos de estudiante o profesor asignados.");
            }
            Profesor profesor = ProfesorMapper.pMapper.profesorInputToProfesor(profesorInput);

            persona.setProfesor(profesor);
            profesor.setPersona(persona);

            ProfesorOutput profesorOutput = ProfesorMapper.pMapper.profesorToProfesorOutput(profesorRepositorio.save(profesor));

            List<Student> students = (profesor.getStudentList());
            List<StudentOutput> studentsO = StreamSupport
                    .stream(students.spliterator(),false)
                    .map(student ->StudentMapper.sMapper.studentToStudentOutput(student)).toList();
            profesorOutput.setStudents(studentsO);


            return profesorOutput;
        }catch(NoSuchElementException e){
            throw new EntityNotFoundException("No se encontró la persona con el id: " + id_usuario);
        }
    }

    @Override
    public ProfesorOutput getProfesorById(int id_profesor) {
        try {
            Profesor profesor = profesorRepositorio.findById(id_profesor).orElseThrow();
            ProfesorOutput profesorOutput = ProfesorMapper.pMapper.profesorToProfesorOutput(profesor);

            List<Student> students = profesor.getStudentList();
            List<StudentOutput> studentsOutput = StreamSupport.stream(students.spliterator(),false)
                    .map(student->StudentMapper.sMapper.studentToStudentOutput(student))
                    .toList();
            profesorOutput.setStudents(studentsOutput);

            return profesorOutput;
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("No se encontró el profesor con id: " + id_profesor);
        }
    }

    @Override
    public ProfesorFullOutput getProfesorById2(int id_profesor) {
        Profesor profesor = profesorRepositorio.findById(id_profesor).orElseThrow();
        ProfesorFullOutput profesorFullOutput = profesor.profesorToProfesorFullOutput();

        return profesorFullOutput;
    }

    @Override
    public List<ProfesorOutput> getAllProfesores(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        List <Profesor> profesores = profesorRepositorio.findAll(pageRequest).getContent();
        List<ProfesorOutput> profesoresO = StreamSupport
                                           .stream(profesores.spliterator(),false)
                                           .map(profesor -> ProfesorMapper.pMapper.profesorToProfesorOutput(profesor)).toList();


        return profesoresO;
    }

    @Override
    public ProfesorOutput updateProfesorById(int id_profesor, ProfesorInput profesorInput) {
        try {
            Persona persona = personaRepositorio.findById(profesorInput.getId_persona()).orElseThrow();

            Profesor profe = profesorRepositorio.findById(id_profesor).orElseThrow();

            profe.setRama(profesorInput.getRama());
            profe.setComentarios(profesorInput.getComentarios());

            List<Student> students = profe.getStudentList();
            List<StudentOutput> studentsOutput = StreamSupport.stream(students.spliterator(),false)
                    .map(student->StudentMapper.sMapper.studentToStudentOutput(student))
                    .toList();


            ProfesorOutput profesorOutput = ProfesorMapper.pMapper.profesorToProfesorOutput(profesorRepositorio.save(profe));

            profesorOutput.setStudents(studentsOutput);
            return profesorOutput;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Persona no encontrada");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProfesorById(int id_profesor) {
        try {
            profesorRepositorio.findById(id_profesor).orElseThrow();
            profesorRepositorio.deleteById(id_profesor);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public void checkInformationProfesor(ProfesorInput profesorInput) throws Exception {
        if (profesorInput.getRama() == null) {
            throw new UnprocessableEntityException("La rama no puede ser nula");
        }
    }
}
