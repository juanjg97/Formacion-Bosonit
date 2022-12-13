package com.bosonit.springdatavalidation.controllers;

import com.bosonit.springdatavalidation.application.services.PersonaServiceImp;
import com.bosonit.springdatavalidation.controllers.dtos.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.PersonaOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.mappers.PersonaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/persona")
public class PostController {
    //Inyectamos el servicio para la lógica de negocio y crud
    @Autowired
    PersonaServiceImp personaServiceImp;

    //Métodos POST

    @PostMapping("/addPersona")
    public ResponseEntity<PersonaOutput> addPersona(@RequestBody PersonaInput personaInput) throws Exception{

        Persona persona = new Persona();
        PersonaOutput personaOutput = new PersonaOutput();

        //Revisa que la información de personaInput que llega de Postman esté completa, si no manda error
        personaServiceImp.checkInformation(personaInput);

        //Transforma de input a entidad para usar los métodos de lógica de negocio y crud
        persona = PersonaMapper.pMapper.personaInputToPersona(personaInput);

        //Agrega a la persona a la bdd, después conviertela a output
        personaOutput= PersonaMapper.pMapper.personaToPersonaOutput(personaServiceImp.addPersona(persona));

        System.out.println("Persona agregada");

        //Regresa a la personaOutput
        return ResponseEntity.status(HttpStatus.CREATED).body(personaOutput);
    }

    //---------------------------------------------------------------------------------------------
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleValidationExceptions(Exception ex) {
        Logger logger = LoggerFactory.getLogger(PostController.class);
        logger.error("El error es: " + ex.getMessage());
        return "El error es: " + ex.getMessage();
    }
}
