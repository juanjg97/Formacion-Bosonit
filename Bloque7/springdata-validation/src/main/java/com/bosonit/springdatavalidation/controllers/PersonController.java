package com.bosonit.springdatavalidation.controllers;

import com.bosonit.springdatavalidation.application.services.implementations.PersonaServiceImp;
import com.bosonit.springdatavalidation.controllers.dtos.inputs.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.PersonaOutput;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException; //Importamos la excepción personalizada
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/persona")
public class PersonController {
    @Autowired
    PersonaServiceImp personaServiceImp;

//Modificar para que acepte un requestParam**************************************************************************
    @GetMapping("/id/{id}")
    public PersonaOutput getPersonaById(@PathVariable int id,
                                        @RequestParam(value = "outputType",defaultValue = "simple") String outputType) {
        if(outputType.equals("simple")){
            PersonaOutput pO = personaServiceImp.getPersonaById(id);
            return pO;
        }else if(outputType.equals("full")){
            return null;
        }else{
            return null;
        }
    }


    @GetMapping("/usuario/{usuario}")
    public PersonaOutput getPersonaByUsuario(@PathVariable String usuario) {
        PersonaOutput pO=personaServiceImp.getPersonaByUsuario(usuario);

        return pO;
    }


    @GetMapping("/personas")
    public Iterable<PersonaOutput> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                  @RequestParam(defaultValue = "4", required = false) int pageSize) {
        System.out.println("Entrando a la función obtener todas las personas");
        Iterable<PersonaOutput>  personasO = personaServiceImp.getAllPersonas(pageNumber,pageSize);
        return personasO;
    }

    //-------------------------------------------------

    @PostMapping("/addPersona")
    public ResponseEntity<PersonaOutput> addPersona(@RequestBody PersonaInput personaInput) throws Exception{
        PersonaOutput pO= personaServiceImp.addPersona(personaInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(pO);
    }

    @PutMapping("/updatePersona")
    public ResponseEntity<PersonaOutput> updatePersona(@RequestBody PersonaInput personaInput) {
        try {
            PersonaOutput pO = personaServiceImp.updatePersona(personaInput);
            System.out.println("Persona actualizadada");

            return ResponseEntity.ok().body(pO);

        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Persona no encontrada");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<PersonaOutput> deletePersona(@PathVariable int id) {
        PersonaOutput po = personaServiceImp.getPersonaById(id);
        personaServiceImp.deletePersona(id);

        return ResponseEntity.ok().body(po);
    }

}
