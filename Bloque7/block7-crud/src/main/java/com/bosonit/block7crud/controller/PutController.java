package com.bosonit.block7crud.controller;

import com.bosonit.block7crud.aplication.service.PersonServiceImp;
import com.bosonit.block7crud.controller.dto.PersonInputDto;
import com.bosonit.block7crud.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Controller para peticiones web
@RequestMapping("/persona") //Nombre del endpoint
public class PutController {
    //Inyectamos dependencias de la clase StudentServiceImp para usar sus métodos
    @Autowired
    PersonServiceImp personService;

    //PUT: Actualizar información => UPDATE
    @PutMapping
    public ResponseEntity<?> updatePerson(@RequestBody PersonInputDto person) {
        Integer id= person.getId();
        try {
            personService.getPersonById(id);
            return  ResponseEntity.ok().body(personService.addPerson(person));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("person with id "+id+" doesn't exist");
        }
    }
}
