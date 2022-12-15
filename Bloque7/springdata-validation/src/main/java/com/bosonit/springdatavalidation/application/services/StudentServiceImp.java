package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.StudentInput;
import com.bosonit.springdatavalidation.controllers.dtos.StudentOutput;
import com.bosonit.springdatavalidation.domain.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService{

    @Override
    public StudentOutput addStudent(StudentInput studentInput, int id_usuario, int id_profesor) {
        return null;
    }

    @Override
    public StudentOutput getStudentById(int id_student) {
        return null;
    }

    @Override
    public List<StudentOutput> getAllStudents(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public StudentOutput updateStudentById(int id_student, StudentInput studentInput) {
        return null;
    }

    @Override
    public void deleteStudentById(int id_student) {

    }
}
