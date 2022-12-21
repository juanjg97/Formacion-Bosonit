package com.bosonit.block10dockerizeapp.application.services;

import com.bosonit.block10dockerizeapp.DTOS.PersonInput;
import com.bosonit.block10dockerizeapp.DTOS.PersonOutput;

public interface PersonService {
    //Método que devuelve un DTO (Crea un DTO)
    PersonOutput addPerson(PersonInput personaInput);
    //Método que devuelve un DTO (Devuelve un DTO)
    PersonOutput getPersonById(int id);
    //Método que devuelve un DTO (Actualiza un DTO)
    PersonOutput updatePerson(PersonInput personaInput);
    //Método que elimina un DTO
    void deletePersonById( int id);
    //Método para obtener todas las personas
    Iterable<PersonOutput> getAllPersons(int pageNumber, int pageSize);
}
