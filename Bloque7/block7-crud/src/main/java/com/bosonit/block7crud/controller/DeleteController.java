package com.bosonit.block7crud.controller;

import com.bosonit.block7crud.aplication.service.PersonServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //Controller para peticiones web
@RequestMapping("/persona") //Nombre del endpoint
public class DeleteController {
    //Inyectamos dependencias de la clase StudentServiceImp para usar sus métodos
    @Autowired
    PersonServiceImp personService;

    @DeleteMapping
    public ResponseEntity<String> deletePersonById(@RequestParam int id) {
        try {
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("person with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("person with id "+id+" doesn't exist");

        }
    }

}
