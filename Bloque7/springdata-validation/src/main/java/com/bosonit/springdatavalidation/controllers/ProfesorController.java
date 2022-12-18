package com.bosonit.springdatavalidation.controllers;

import com.bosonit.springdatavalidation.application.services.implementations.ProfesorServiceImp;
import com.bosonit.springdatavalidation.controllers.dtos.inputs.ProfesorInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.PersonaOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.ProfesorOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs_full.ProfesorFullOutput;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorServiceImp profesorServiceImp;

    @PostMapping("/add")
    public ResponseEntity<ProfesorOutput> addProfesor(@RequestBody ProfesorInput profesorInput) throws Exception {
        ProfesorOutput pO = profesorServiceImp.addProfesor(profesorInput,profesorInput.getId_persona());
        return ResponseEntity.status(HttpStatus.CREATED).body(pO);
    }
    //Modificar para que acepte un requestParam**************************************************************************
    @GetMapping("/read/{id_profesor}")
    public ResponseEntity<?> getProfesorById(@PathVariable int id_profesor,
                                          @RequestParam(value = "outputType", defaultValue = "simple") String outputType) {
        if(outputType.equals("simple")){
            ProfesorOutput pO = profesorServiceImp.getProfesorById(id_profesor);
            return ResponseEntity.ok().body(pO);
        }else if(outputType.equals("full")){
            ProfesorFullOutput pFO = profesorServiceImp.getProfesorById2(id_profesor);
            return ResponseEntity.ok().body(pFO);
        }else{
            return null;
        }

    }

    @GetMapping("/readAll")
    public List<ProfesorOutput> getAllStudents(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                              @RequestParam(defaultValue = "4", required = false) int pageSize) {
        List<ProfesorOutput> profesoresO = profesorServiceImp.getAllProfesores(pageNumber,pageSize);

        return profesoresO;
    }

    @PutMapping("/update/{id_profesor}")
    public ResponseEntity<ProfesorOutput> updateStudentById(@PathVariable int id_profesor, @RequestBody ProfesorInput profesorInput) {
        try {
            ProfesorOutput pO = profesorServiceImp.updateProfesorById(id_profesor,profesorInput);
            System.out.println("Profesor actualizado");
            return ResponseEntity.ok().body(pO);

        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Estudiante no encontrada");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deteteStudentById(@PathVariable int id) {
        ProfesorOutput pO = profesorServiceImp.getProfesorById(id);
        profesorServiceImp.deleteProfesorById(id);

        return ResponseEntity.ok().body("Se eliminó al profesor: "+pO);
    }

}
