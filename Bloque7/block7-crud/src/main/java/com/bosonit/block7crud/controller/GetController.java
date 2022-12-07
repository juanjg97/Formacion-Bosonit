package com.bosonit.block7crud.controller;

import com.bosonit.block7crud.aplication.service.PersonServiceImp;
import com.bosonit.block7crud.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Controller para peticiones web
@RequestMapping("/persona") //Nombre del endpoint
public class GetController {
    //Inyectamos dependencias de la clase StudentServiceImp para usar sus métodos
    @Autowired
    PersonServiceImp personService;

    //GET: Para obtener información => READ
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id) {
        try {
            PersonOutputDto persona = this.personService.getPersonById(id); //¿Por qué this?
            return ResponseEntity.ok().body(persona);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("person with id "+id+" doesn't exist");
        }
    }

    @GetMapping
    public Iterable<PersonOutputDto> getAllPersons(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personService.getAllPersons(pageNumber, pageSize);
    }
}
