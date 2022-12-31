package com.bosonit.block13mongodb.controllers;

import com.bosonit.block13mongodb.application.services.PersonService;
import com.bosonit.block13mongodb.dtos.PersonInput;
import com.bosonit.block13mongodb.dtos.PersonOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutput addPersona(@RequestBody PersonInput personInput) throws Exception {
        return personService.addPerson(personInput);

    }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public PersonOutput getPersonaById(@RequestParam String id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<PersonOutput> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                     @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personService.getAllPersons(pageNumber, pageSize);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutput updatePersonById(@RequestBody PersonInput personInput) {
        return personService.updatePersonById(personInput);
    }

    @DeleteMapping("/delete/id")
    public ResponseEntity<String> deletePersonaByI(@RequestParam String id) {
        PersonOutput personOutput = personService.getPersonById(id);
        personService.deletePersonById(id);
        String body = "La persona con los datos: "+personOutput +" ha sido eliminada";
        return  new ResponseEntity<>(body,HttpStatus.OK);
    }
}
