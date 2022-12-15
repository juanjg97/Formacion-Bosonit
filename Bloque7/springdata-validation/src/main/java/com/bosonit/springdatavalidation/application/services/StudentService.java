package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.StudentInput;
import com.bosonit.springdatavalidation.controllers.dtos.StudentOutput;
import com.bosonit.springdatavalidation.domain.entities.Student;

import java.util.List;

public interface StudentService {
    StudentOutput addStudent(StudentInput studentInput, int id_usuario, int id_profesor);
    StudentOutput getStudentById(int id_student);
    List<StudentOutput> getAllStudents(int pageNumber, int pageSize);
    StudentOutput updateStudentById(int id_student, StudentInput studentInput);
    void deleteStudentById(int id_student);
}
