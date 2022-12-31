package com.bosonit.block13mongodb.mappers;


import com.bosonit.block13mongodb.domain.entities.Person;
import com.bosonit.block13mongodb.dtos.PersonInput;
import com.bosonit.block13mongodb.dtos.PersonOutput;
import org.springframework.stereotype.Service;

@Service
public class PersonMapperImp implements PersonMapper{

    @Override
    public Person personInputToPersona(PersonInput personaInput) {
       Person person = new Person();
       person.setId(personaInput.getId());
       person.setUser(personaInput.getUser());
       person.setPassword(personaInput.getPassword());
       person.setName(personaInput.getName());
       person.setSurname(personaInput.getSurname());
       person.setCompany_email(personaInput.getCompany_email());
       person.setPersonal_email(personaInput.getCompany_email());

       return person;

    }

    @Override
    public PersonOutput personToPersonaOutput(Person person) {
        PersonOutput personOutput = new PersonOutput();
        personOutput.setUser(person.getUser());
        personOutput.setName(person.getName());
        personOutput.setSurname(person.getSurname());
        personOutput.setCompany_email(person.getCompany_email());
        personOutput.setPersonal_email(person.getCompany_email());
        return personOutput;
    }
}
