package com.bosonit.springdatavalidation.application.services.interfaces;

import com.bosonit.springdatavalidation.controllers.dtos.inputs.StudentInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.StudentOutput;

import java.util.List;

public interface StudentService {
    StudentOutput addStudent(StudentInput studentInput, int id_usuario, int id_profesor) throws Exception;
    StudentOutput getStudentById(int id_student);
    List<StudentOutput> getAllStudents(int pageNumber, int pageSize);
    StudentOutput updateStudentById(int id_student, StudentInput studentInput);
    void deleteStudentById(int id_student);
}
