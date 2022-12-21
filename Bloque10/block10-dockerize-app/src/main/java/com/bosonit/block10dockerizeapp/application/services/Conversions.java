package com.bosonit.block10dockerizeapp.application.services;

import com.bosonit.block10dockerizeapp.DTOS.PersonInput;
import com.bosonit.block10dockerizeapp.DTOS.PersonOutput;
import com.bosonit.block10dockerizeapp.domain.entities.Person;

public interface Conversions {
    Person personaInputToPersona(PersonInput personaInput);
    PersonOutput personToPersonOutput(Person persona);

}
