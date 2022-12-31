package com.bosonit.block13mongodb.mappers;

import com.bosonit.block13mongodb.domain.entities.Person;
import com.bosonit.block13mongodb.dtos.PersonInput;
import com.bosonit.block13mongodb.dtos.PersonOutput;

public interface PersonMapper {
    Person personInputToPersona(PersonInput personaInput);
    PersonOutput personToPersonaOutput(Person person);
}
