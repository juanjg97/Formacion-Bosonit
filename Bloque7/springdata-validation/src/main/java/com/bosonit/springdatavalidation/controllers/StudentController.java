package com.bosonit.springdatavalidation.controllers;


import com.bosonit.springdatavalidation.application.services.StudentServiceImp;
import com.bosonit.springdatavalidation.controllers.dtos.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.PersonaOutput;
import com.bosonit.springdatavalidation.controllers.dtos.StudentInput;
import com.bosonit.springdatavalidation.controllers.dtos.StudentOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.domain.entities.Student;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentServiceImp studentServiceImp;

    @PostMapping
    public ResponseEntity<StudentOutput> addStudent(@RequestBody StudentInput studentInput) throws Exception {
        StudentOutput sO = studentServiceImp.addStudent(studentInput,studentInput.getId_usuario(),studentInput.getId_profesor());

        return ResponseEntity.status(HttpStatus.CREATED).body(sO);
    }

    @GetMapping("{id_student}")
    public StudentOutput getStudentById(@PathVariable int id_student) {
        StudentOutput sO = studentServiceImp.getStudentById(id_student);
        return sO;
    }

    @GetMapping("/estudiantes")
    public List<StudentOutput> getAllStudents(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                              @RequestParam(defaultValue = "4", required = false) int pageSize) {

        List<StudentOutput>  estudiantesO = studentServiceImp.getAllStudents(pageNumber,pageSize);
        return estudiantesO;
    }

    @PutMapping("/updateEstudiante/{id_student}")
    public ResponseEntity<StudentOutput> updateStudentById(@PathVariable int id_student, @RequestBody StudentInput studentInput) {
        try {
            StudentOutput sO = studentServiceImp.updateStudentById(id_student,studentInput);
            System.out.println("Estudiante actualizado");
            return ResponseEntity.ok().body(sO);

        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Estudiante no encontrada");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deteteStudentById(@PathVariable int id) {
        StudentOutput sO = studentServiceImp.getStudentById(id);
        studentServiceImp.deleteStudentById(id);
        System.out.println(sO);
        return ResponseEntity.ok().body("Se eliminó al estudiante: "+sO);
    }


}
