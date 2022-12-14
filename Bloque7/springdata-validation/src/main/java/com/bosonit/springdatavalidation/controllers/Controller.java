package com.bosonit.springdatavalidation.controllers;

import com.bosonit.springdatavalidation.application.services.PersonaServiceImp;
import com.bosonit.springdatavalidation.controllers.dtos.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.PersonaOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException; //Importamos la excepción personalizada
import com.bosonit.springdatavalidation.mappers.PersonaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/persona")
public class Controller {
    //Inyectamos el servicio para la lógica de negocio y crud
    @Autowired
    PersonaServiceImp personaServiceImp;


    @GetMapping("/id/{id}")
    public PersonaOutput getPersonaById(@PathVariable int id) {
        Persona persona = new Persona();
        PersonaOutput personaOutput = new PersonaOutput();

        //Obten de la bdd a la persona que tiene el id indicado
        persona = personaServiceImp.getPersonaById(id);

        //Transforma a la persona de entidad a output
        personaOutput= PersonaMapper.pMapper.personaToPersonaOutput(persona);

        //Retorna a la persona output
        return personaOutput;
    }

    @GetMapping("/usuario/{usuario}")
    public PersonaOutput getPersonaByUsuario(@PathVariable String usuario) {
        Persona persona = new Persona();
        PersonaOutput personaOutput = new PersonaOutput();

        //Obten de la bdd a la persona que tiene el usuario indicado
        persona=personaServiceImp.getPersonaByUsuario(usuario);

        //Transforma a la persona de entidad a output
        personaOutput = PersonaMapper.pMapper.personaToPersonaOutput(persona);

        //Retorna a la persona output
        return personaOutput;
    }


    @GetMapping("/personas")
    public Iterable<PersonaOutput> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                  @RequestParam(defaultValue = "4", required = false) int pageSize) {

        Iterable <Persona> personas;
        Iterable<PersonaOutput> personasOutput;

        //Obten a todas las personas de la bdd, son entidades
        personas = personaServiceImp.getAllPersonas(pageNumber,pageSize);

        //Transforma esas entidades a output
        personasOutput = StreamSupport.stream(personas.spliterator(),false).map(persona -> PersonaMapper.pMapper.personaToPersonaOutput(persona)).toList();

        //Regresa a las personas de tipo output
        return personasOutput;

    }

    //-------------------------------------------------

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

    @PutMapping("/updatePersona")
    public ResponseEntity<PersonaOutput> updatePersona(@RequestBody PersonaInput personaInput) {
        try {
            Persona persona = new Persona();
            PersonaOutput personaOutput = new PersonaOutput();

            persona = PersonaMapper.pMapper.personaInputToPersona(personaInput);

            personaOutput= PersonaMapper.pMapper.personaToPersonaOutput(personaServiceImp.updatePersona(persona));
            System.out.println("Persona actualizadada");
            return ResponseEntity.ok().body(personaOutput);

        } catch (NoSuchElementException e) {
            //Usamos la excepción que creamos
            throw new EntityNotFoundException("Persona no encontrada");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<PersonaOutput> deletePersona(@PathVariable int id) {
        Persona persona = personaServiceImp.getPersonaById(id);
        PersonaOutput personaOutput = PersonaMapper.pMapper.personaToPersonaOutput(persona);
        personaServiceImp.deletePersona(id);
        return ResponseEntity.ok().body(personaOutput);
    }

}
