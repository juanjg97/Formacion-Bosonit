package com.bosonit.springdatavalidation.controllers;

import com.bosonit.springdatavalidation.application.services.implementations.PersonaServiceImp;
import com.bosonit.springdatavalidation.controllers.dtos.inputs.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.PersonaOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.ProfesorOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs_full.PersonaFullOutput;
import com.bosonit.springdatavalidation.domain.entities.feign.ProfesorFeign;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
//@RequestMapping("/persona")
public class PersonController {
    @Autowired
    PersonaServiceImp personaServiceImp;
    @Autowired
    ProfesorFeign profesorFeign;

    @CrossOrigin(origins= "https://cdpn.io")
    @PostMapping("/addperson")
    public ResponseEntity<PersonaOutput> addPersona(@RequestBody PersonaInput personaInput) throws Exception{
        PersonaOutput pO= personaServiceImp.addPersona(personaInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(pO);
    }

    @CrossOrigin(origins= "https://cdpn.io")
    @GetMapping("/getall")
    public Iterable<PersonaOutput> getAllPersonas(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                  @RequestParam(defaultValue = "4", required = false) int pageSize) {
        System.out.println("Entrando a la función obtener todas las personas");
        Iterable<PersonaOutput>  personasO = personaServiceImp.getAllPersonas(pageNumber,pageSize);
        return personasO;
    }

//Modificar para que acepte un requestParam**************************************************************************
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable int id,
                                        @RequestParam(value = "outputType",defaultValue = "simple") String outputType) {
        if(outputType.equals("simple")){
            PersonaOutput pO = personaServiceImp.getPersonaById(id);
            return ResponseEntity.ok().body(pO);
        }else if(outputType.equals("full")){
            PersonaFullOutput pFO = personaServiceImp.getPersonaById2(id);
            return ResponseEntity.ok().body(pFO);
        }else{
            return null;
        }
    }


    @GetMapping("/usuario/{usuario}")
    public PersonaOutput getPersonaByUsuario(@PathVariable String usuario) {
        PersonaOutput pO=personaServiceImp.getPersonaByUsuario(usuario);

        return pO;
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
    @GetMapping("profesor/{id}")
    public ProfesorOutput getProfesor(@PathVariable String id) {
        return profesorFeign.getProfesorById(id, "simple");
    }

    @GetMapping("person/fields")
    public List<PersonaOutput> personByFields(@RequestParam(name = "usuario", required = false) String usuario,
                                              @RequestParam(name = "name", required = false) String name,
                                              @RequestParam(name = "surname", required = false) String surname,
                                              @RequestParam(name = "created_date", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") Date created_date,
                                              @RequestParam(name = "orderBy", defaultValue = "usuario", required = false) String orderBy) throws Exception {

        List<PersonaOutput> personasOutput = personaServiceImp.personByFields(usuario, name, surname, created_date, orderBy);

        return personasOutput;
    }

    @GetMapping("person/page")
    public Page<PersonaOutput> allPersonByPage(@RequestParam(value = "offset") int offset,
                                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<PersonaOutput> personasOutput = personaServiceImp.searchAllWithPagination(offset, pageSize);

        return personasOutput;
    }

}
