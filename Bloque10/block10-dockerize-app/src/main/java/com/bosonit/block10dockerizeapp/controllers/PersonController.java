package com.bosonit.block10dockerizeapp.controllers;


import com.bosonit.block10dockerizeapp.DTOS.PersonInput;
import com.bosonit.block10dockerizeapp.DTOS.PersonOutput;
import com.bosonit.block10dockerizeapp.application.services.PersonServiceImp;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonServiceImp personServiceImp;

    @GetMapping("/saludar")
    public ResponseEntity<?> getPersonaById() {
            return ResponseEntity.ok().body("Hello World!");
    }

    @PostMapping()
    public ResponseEntity<?> addPersona(@RequestBody PersonInput personaInput) throws Exception{
        PersonOutput pO= personServiceImp.addPerson(personaInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(pO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable int id) {
            PersonOutput pO = personServiceImp.getPersonById(id);
            return ResponseEntity.ok().body(pO);
    }

    @PutMapping()
    public ResponseEntity<PersonOutput> updatePersona(@RequestBody PersonInput personaInput) {
        try {
            PersonOutput pO = personServiceImp.updatePerson(personaInput);
            return ResponseEntity.ok().body(pO);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Persona no encontrada");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<PersonOutput> deletePersona(@PathVariable int id) {
        PersonOutput po = personServiceImp.getPersonById(id);
        personServiceImp.deletePersonById(id);
        return ResponseEntity.ok().body(po);
    }

    @GetMapping("/personas")
    public Iterable<PersonOutput> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                  @RequestParam(defaultValue = "4", required = false) int pageSize) {
        System.out.println("Entrando a la función obtener todas las personas");
        Iterable<PersonOutput>  personasO = personServiceImp.getAllPersons(pageNumber,pageSize);
        return personasO;
    }


}
