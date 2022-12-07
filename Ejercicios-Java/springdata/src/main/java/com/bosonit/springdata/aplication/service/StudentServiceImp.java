package com.bosonit.springdata.aplication.service;

import com.bosonit.springdata.controller.dto.StudentInputDto;
import com.bosonit.springdata.controller.dto.StudentOutputDto;
import com.bosonit.springdata.domain.entity.Student;
import com.bosonit.springdata.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
/*
*Clase que implementa los métodos de la lógica de negocio de la interface StudentService
*
*Vamos a inyectar el repository para poder usar sus métodos CRUD.
*
*El repositorio trabaja con objetos de clase Student, para hacer cualquier consulta que involucre
*objetos StudentDto hay que transformar los DTO a Student.
*
*Llega a la función un objeto StudentInputDto, creamos objetos Student con el StudentInputDto para poder trabajar
* con el repositorio, después transformamos a StudentOutputDto y lo regresamos.
*/

@Service //Escribimos esta etiqueta para que spring instancee esta clase
public class StudentServiceImp implements StudentService{
    //Bean inyectado de la clase StudentRepositoru (esta clase la creo automáticamente Spring)
    //Definimos un objeto del tipo studentRepository, ya que ahí se encuentran los métodos del CRUD
    @Autowired
    StudentRepository studentRepository;


    //CREATE
    @Override
    public StudentOutputDto addStudent(StudentInputDto student) {
        Student estudiante = new Student(student); //Creamos un Student usando StudentInputDto
        StudentOutputDto estudianteDto = studentRepository //Usamos los métodos del repositorio
                                        .save(estudiante)  //Guardamos al estudiante de tipo Student
                                        .studentToStudentOutputDto(); //Transformamos de Student  a StudentOutputDto
        return estudianteDto; //Regresamos un estudiante de tipo Dto
    }

    //READ
    /*El método findById devuelve un Optional. El método orElseThrow del objeto optional nos devuelve el dato si el optional contiene datos,
    o una excepción NoSuchElementException en caso contrario.*/
    @Override
    public StudentOutputDto getStudentById(int id) {
        Student estudiante = studentRepository //Usamos los métodos del repositorio
                            .findById(id)      //Revisa si existe un estudiante con ese id
                            .orElseThrow();    //Devuelve el estudianteDto si existe, si no una excepción NoSuchElementException
        StudentOutputDto estudianteDto = estudiante.studentToStudentOutputDto();//Transformamos de Student a StudentOutputDto

        return estudianteDto;
    }
    /*Las consultas que devuelvan múltiples resultados, es necesario paginarlo.*/
    @Override
    public List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return studentRepository.findAll(pageRequest)
                                .getContent()
                                .stream()
                                .map(Student::studentToStudentOutputDto)
                                .toList();
    }

    //UPDATE
    @Override
    public StudentOutputDto updateStudent(StudentInputDto student) {
        int id = student.getId();
                studentRepository //Usamos los métodos del repositorio
                .findById(id)     //Revisa si existe un estudiante con ese id
                .orElseThrow();   //Devuelve el estudianteDto si existe, si no una excepción NoSuchElementException

        Student estudianteUpdated = new Student(student); //Crea un nuevo estudiante con los parámetros de la función (actualiza datos)

        StudentOutputDto estudianteDto = studentRepository            //Usamos los métodos del repositorio
                                        .save(estudianteUpdated)      //Guardamos al estudiante de tipo Student
                                        .studentToStudentOutputDto(); //Transformamos de Student  a StudentOutputDto
        return estudianteDto;
    }

    //DELETE
    @Override
    public void deleteStudentById(int id) {
        studentRepository.findById(id).orElseThrow();
        studentRepository.deleteById(id);
    }

}
