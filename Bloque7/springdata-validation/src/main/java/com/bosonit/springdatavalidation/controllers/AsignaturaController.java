package com.bosonit.springdatavalidation.controllers;

import com.bosonit.springdatavalidation.application.services.AsignaturaServiceImp;
import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaInput;
import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaOutput;
import com.bosonit.springdatavalidation.repositories.AsignaturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {
    @Autowired
    AsignaturaServiceImp asignaturaServiceImp;

    @Autowired
    AsignaturaRepositorio asignaturaRepositorio;

    @PostMapping("/add")
    public ResponseEntity<AsignaturaOutput> addAsignatura(@RequestBody AsignaturaInput asignaturaInput) throws Exception {
        AsignaturaOutput aO = asignaturaServiceImp.addAsignatura(asignaturaInput);

        return ResponseEntity.status(HttpStatus.CREATED).body(aO);
    }

    @GetMapping("/read/{id_asignatura}")
    public ResponseEntity<AsignaturaOutput> getAsignaturaById(@PathVariable int id_asignatura) {
        AsignaturaOutput aO = asignaturaServiceImp.getAsignaturaById(id_asignatura);

        return ResponseEntity.ok().body(aO);
    }

    @GetMapping("/readAll")
    public ResponseEntity<List<AsignaturaOutput>> getAllAsignaturas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                  @RequestParam(defaultValue = "4", required = false) int pageSize) {

        List<AsignaturaOutput> asignaturasO = asignaturaServiceImp.getAllAsignaturas(pageNumber,pageSize);

        return ResponseEntity.ok().body(asignaturasO);
    }

    @PutMapping("/update/{id_asignatura}")
    public ResponseEntity<AsignaturaOutput> updateAsignaturaById(@PathVariable int id_asignatura, @RequestBody AsignaturaInput asignaturaInput) {
        AsignaturaOutput aO = asignaturaServiceImp.updateAsignatura(id_asignatura,asignaturaInput);
        return ResponseEntity.ok().body(aO);
    }

    @DeleteMapping("delete/{id_asignatura}")
    public ResponseEntity<?> deteteStudentById(@PathVariable int id_asignatura) {

        AsignaturaOutput aO = asignaturaServiceImp.getAsignaturaById(id_asignatura);
        asignaturaServiceImp.deleteAsignaturaById(id_asignatura);

        return ResponseEntity.ok().body("Se eliminó la asignatura: "+aO);
    }
}