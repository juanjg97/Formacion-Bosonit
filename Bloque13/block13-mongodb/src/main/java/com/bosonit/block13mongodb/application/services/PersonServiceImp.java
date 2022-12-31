package com.bosonit.block13mongodb.application.services;

import com.bosonit.block13mongodb.domain.entities.Person;
import com.bosonit.block13mongodb.dtos.PersonInput;
import com.bosonit.block13mongodb.dtos.PersonOutput;
import com.bosonit.block13mongodb.exceptions.UnprocessableEntityException;
import com.bosonit.block13mongodb.mappers.PersonMapper;
import com.bosonit.block13mongodb.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PersonServiceImp implements PersonService{
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonMapper personMapper;

    @Override
    public PersonOutput addPerson(PersonInput personInput) throws Exception {
        Person p = personMapper.personInputToPersona(personInput);
        Optional<Person> personOptional = personRepository.findPersonByUser(p.getUser());
        validarDatosPersona(personInput,personOptional);
        return personMapper.personToPersonaOutput(personRepository.save(p));
    }

    @Override
    public PersonOutput getPersonById(String idPerson) {
        Person p = personRepository
                .findById(idPerson)
                .orElseThrow(() -> new UnprocessableEntityException("Persona no encontrada"));

        return personMapper.personToPersonaOutput(p);
    }

    @Override
    public Iterable<PersonOutput> getAllPersons(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).stream().map(person -> personMapper.personToPersonaOutput(person)).toList();
    }

    @Override
    public PersonOutput updatePersonById(PersonInput personInput) {
        String idPerson = personInput.getId();
        Person p = personRepository
                .findById(idPerson)
                .orElseThrow(() -> new UnprocessableEntityException("Persona no encontrada"));

        p.setSurname(personInput.getSurname());
        p.setName(personInput.getName());
        p.setUser(personInput.getUser());
        p.setPassword(personInput.getPassword());
        p.setPersonal_email(personInput.getPersonal_email());
        p.setCompany_email(personInput.getCompany_email());
        personRepository.save(p);
        return personMapper.personToPersonaOutput(p);
    }

    @Override
    public void deletePersonById(String idPerson) {
        Person p = personRepository
                .findById(idPerson)
                .orElseThrow(() -> new UnprocessableEntityException("Persona no encontrada"));
        personRepository.deleteById(idPerson);
    }

    public void validarDatosPersona(PersonInput personInput,Optional<Person> personOptional) {
        try {
            Objects.requireNonNull(personInput.getName(), "Name no puede ser nulo");
            Objects.requireNonNull(personInput.getSurname(), "Surname no puede ser nulo");
            Objects.requireNonNull(personInput.getUser(), "Usuario no puede ser nulo");
            Objects.requireNonNull(personInput.getPassword(), "Password no puede ser nulo");
            Objects.requireNonNull(personInput.getCompany_email(), "company_email no puede ser nulo");
            Objects.requireNonNull(personInput.getPersonal_email(), "personal_email no puede ser nulo");
        } catch (NullPointerException e) {
            throw new UnprocessableEntityException(e.getMessage());
        } if (personOptional.isPresent()) {
            throw new UnprocessableEntityException("La persona ya está registrada");
        }
    }
}
