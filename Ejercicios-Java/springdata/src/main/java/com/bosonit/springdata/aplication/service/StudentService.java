package com.bosonit.springdata.aplication.service;

import com.bosonit.springdata.controller.dto.StudentInputDto;
import com.bosonit.springdata.controller.dto.StudentOutputDto;

/*
* Interfaz que va a implementar los métodos para la lógica de negocio CRUD
*
*Service se van a comunicar con objetos de clase Student.
*
* Estos métodos los vamos a implementar en el Controller
*/

public interface StudentService {
    //Método que devuelve un DTO (Crea un DTO)
    StudentOutputDto addStudent(StudentInputDto student);
    //Método que devuelve un DTO (Devuelve un DTO)
    StudentOutputDto getStudentById(int id);
    //Método que devuelve un DTO (Actualiza un DTO)
    StudentOutputDto updateStudent(StudentInputDto student);
    //Método que elimina un DTO
    void deleteStudentById( int id);
    //---- ¿?
    Iterable<StudentOutputDto> getAllStudents(int pageNumber, int pageSize);

}
