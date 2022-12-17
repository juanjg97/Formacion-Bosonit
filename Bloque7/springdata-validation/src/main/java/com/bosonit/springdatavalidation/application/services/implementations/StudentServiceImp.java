package com.bosonit.springdatavalidation.application.services.implementations;

import com.bosonit.springdatavalidation.application.services.interfaces.StudentService;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.AsignaturaOutput;
import com.bosonit.springdatavalidation.controllers.dtos.inputs.StudentInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.StudentOutput;
import com.bosonit.springdatavalidation.domain.entities.Asignatura;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.domain.entities.Profesor;
import com.bosonit.springdatavalidation.domain.entities.Student;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import com.bosonit.springdatavalidation.exceptions.UnprocessableEntityException;
import com.bosonit.springdatavalidation.mappers.AsignaturaMapper;
import com.bosonit.springdatavalidation.mappers.StudentMapper;
import com.bosonit.springdatavalidation.repositories.AsignaturaRepositorio;
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
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentRepositorio studentRepositorio;
    @Autowired
    PersonaRepositorio personaRepositorio;
    @Autowired
    ProfesorRepositorio profesorRepositorio;

    @Autowired
    PersonaServiceImp personaServiceImp;

    @Autowired
    ProfesorServiceImp profesorServiceImp;

    @Autowired
    AsignaturaRepositorio asignaturaRepositorio;

    @Override
    public StudentOutput addStudent(StudentInput studentInput, int id_usuario, int id_profesor) throws Exception {
        try{
            checkInformationStudent(studentInput);

            Persona persona = personaRepositorio.findById(id_usuario).orElseThrow();
            Profesor profesor = profesorRepositorio.findById(id_profesor).orElseThrow();

            if(persona.getStudent()!=null || persona.getProfesor() != null){
                throw new UnprocessableEntityException("La persona con id: "+id_usuario + "ya tiene los datos asignados");
            }

            Student student = StudentMapper.sMapper.studentInputToStudent(studentInput);

            persona.setStudent(student);
            student.setPersona(persona);

            List<Student> studentsList = profesor.getStudentList();
            studentsList.add(student);
            profesor.setStudentList(studentsList);

            student.setProfesor(profesor);

            profesorRepositorio.save(profesor);

            StudentOutput studentOutput = StudentMapper.sMapper.studentToStudentOutput(studentRepositorio.save(student));

            return studentOutput;
        }catch(NoSuchElementException e){
            throw new EntityNotFoundException("No se encontró usuario con  el id: " + id_usuario);
        }
    }

    @Override
    public StudentOutput getStudentById(int id_student) {
        try {
            Student student = studentRepositorio.findById(id_student).orElseThrow();

            StudentOutput studentOutput = StudentMapper.sMapper.studentToStudentOutput(student);

            List<Asignatura> asignaturas = student.getAsignaturaList();
            List<AsignaturaOutput> asignaturasOutput = StreamSupport.stream(asignaturas.spliterator(),false)
                    .map(asignatura -> AsignaturaMapper.aMapper.asignaturaToAsignaturaOutput(asignatura)).toList();

            studentOutput.setAsignaturas(asignaturasOutput);

            return studentOutput;
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("No se encontró el id: " + id_student);
        }
    }

    @Override
    public List<StudentOutput> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        List <Student> estudiantes = studentRepositorio.findAll(pageRequest).getContent();
        List<StudentOutput> estudiantesO = StreamSupport
                                          .stream(estudiantes.spliterator(),false)
                                          .map(estudiante -> StudentMapper.sMapper.studentToStudentOutput(estudiante)).toList();

        return estudiantesO;
    }

    @Override
    public StudentOutput updateStudentById(int id_student, StudentInput studentInput) {
        try {
            Profesor profesor = profesorRepositorio.findById(studentInput.getId_profesor()).orElseThrow();
            Persona  persona = personaRepositorio.findById(studentInput.getId_usuario()).orElseThrow();

            Student oldStudent = studentRepositorio.findById(id_student).orElseThrow();
            Student newStudent = StudentMapper.sMapper.studentInputToStudent(studentInput);

            newStudent.setProfesor(profesor);
            newStudent.setPersona(persona);
            newStudent.setId_student(oldStudent.getId_student());


            List<Student> studentsList = profesor.getStudentList();
            studentsList.add(newStudent);
            profesor.setStudentList(studentsList);
            profesorRepositorio.save(profesor);

            StudentOutput estudianteOutput=StudentMapper.sMapper.studentToStudentOutput(studentRepositorio.save(newStudent));
           return estudianteOutput;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Persona no encontrada");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudentById(int id_student) {
        try {
            studentRepositorio.findById(id_student).orElseThrow();
            studentRepositorio.deleteById(id_student);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    public void checkInformationStudent(StudentInput studentInput) throws Exception {
        if (studentInput.getNum_hours_week() == 0) {
            throw new UnprocessableEntityException("Usuario no puede ser nulo");
        } else if (studentInput.getRama()==null) {
            throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");
        }
    }
}
