package com.bosonit.block13mongodb.application.services;

import com.bosonit.block13mongodb.domain.entities.Person;
import com.bosonit.block13mongodb.dtos.PersonInput;
import com.bosonit.block13mongodb.dtos.PersonOutput;

public interface PersonService {
    PersonOutput addPerson(PersonInput personInput) throws Exception;

    PersonOutput getPersonById(String idPersona);

    Iterable<PersonOutput> getAllPersons(int pageNumber, int pageSize);

    PersonOutput updatePersonById(PersonInput personInput);

    void deletePersonById(String idPersona);

}
