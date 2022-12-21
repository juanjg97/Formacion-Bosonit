package com.bosonit.block10dockerizeapp.application.services;

import com.bosonit.block10dockerizeapp.DTOS.PersonInput;
import com.bosonit.block10dockerizeapp.DTOS.PersonOutput;
import com.bosonit.block10dockerizeapp.domain.entities.Person;
import com.bosonit.block10dockerizeapp.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImp implements PersonService{

    @Autowired PersonRepository personRepository;
    @Autowired ConversionsImp conversionsImp;

    @Override
    public PersonOutput addPerson(PersonInput personaInput) {

        Person p = conversionsImp.personaInputToPersona(personaInput);
        PersonOutput pO = conversionsImp.personToPersonOutput(personRepository.save(p));

        return pO;
    }

    @Override
    public PersonOutput getPersonById(int id) {
        try {
            Person p = personRepository.findById(id).orElseThrow();
            PersonOutput pO = conversionsImp.personToPersonOutput(p);
            return pO;
        }catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public PersonOutput updatePerson(PersonInput personaInput) {
        try {
            Person p = personRepository.findById(personaInput.getId()).orElseThrow();
            p.setId(personaInput.getId());
            p.setEdad(personaInput.getEdad());
            p.setNombre(personaInput.getNombre());
            p.setPoblacion(personaInput.getPoblacion());

            return conversionsImp.personToPersonOutput(personRepository.save(p));
        }catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + personaInput.getId());
        }
    }

    @Override
    public void deletePersonById(int id) {
        try {
            personRepository.findById(id).orElseThrow();
            personRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(e.getMessage());
        }

    }

    @Override
    public Iterable<PersonOutput> getAllPersons(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Iterable <Person> personas = personRepository.findAll(pageRequest).getContent();
        Iterable<PersonOutput> personasO = StreamSupport
                .stream(personas.spliterator(),false)
                .map(persona -> conversionsImp.personToPersonOutput(persona)).toList();

        return personasO;
    }
}
