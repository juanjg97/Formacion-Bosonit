package com.bosonit.block7crud.aplication.service;

import com.bosonit.block7crud.controller.dto.PersonInputDto;
import com.bosonit.block7crud.controller.dto.PersonOutputDto;

/*
 * Interfaz que va a implementar los métodos para la lógica de negocio CRUD
 *
 *Service se van a comunicar con objetos de clase Person.
 *
 * Estos métodos los vamos a implementar en el Controller
 *
 * Esta capa recibe y regresa DTOS por lo que hay que tener en cuenta esa conversión.
 */
public interface PersonService {
    //Método que devuelve un DTO (Crea un DTO)
    PersonOutputDto addPerson(PersonInputDto person);
    //Método que devuelve un DTO (Devuelve un DTO)
    PersonOutputDto getPersonById(int id);
    //Método que devuelve un DTO (Actualiza un DTO)
    PersonOutputDto updatePerson(PersonInputDto person);
    //Método que elimina un DTO
    void deletePersonById( int id);
    //---- ¿?
    Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize);
}
