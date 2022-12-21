package com.bosonit.block10dockerizeapp.application.services;

import com.bosonit.block10dockerizeapp.DTOS.PersonInput;
import com.bosonit.block10dockerizeapp.DTOS.PersonOutput;
import com.bosonit.block10dockerizeapp.domain.entities.Person;
import org.springframework.stereotype.Service;

@Service
public class ConversionsImp implements Conversions{

    public Person personaInputToPersona(PersonInput personaInput) {

        Person p = new Person();

        p.setId(personaInput.getId());
        p.setEdad(personaInput.getEdad());
        p.setNombre(personaInput.getNombre());
        p.setPoblacion(personaInput.getPoblacion());

        return p;
    }


    public PersonOutput personToPersonOutput(Person persona) {
        PersonOutput pO = new PersonOutput();

        pO.setEdad(persona.getEdad());
        pO.setNombre(persona.getNombre());
        pO.setPoblacion(persona.getPoblacion());
        pO.setId(persona.getId());

        return pO;

    }
}
