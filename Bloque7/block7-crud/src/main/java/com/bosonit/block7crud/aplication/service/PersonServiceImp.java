package com.bosonit.block7crud.aplication.service;
/*
 *Clase que implementa los métodos de la lógica de negocio de la interface PersonService
 *
 *Vamos a inyectar el repository para poder usar sus métodos CRUD.
 *
 *El repositorio trabaja con objetos de clase Person, para hacer cualquier consulta que involucre
 *objetos PersonDto hay que transformar los DTO a Person.
 *
 *Llega a la función un objeto PersonInputDto, creamos objetos Person con el PersonInputDto para poder trabajar
 * con el repositorio, después transformamos a PersonOutputDto y lo regresamos.
 */

import com.bosonit.block7crud.controller.dto.PersonInputDto;
import com.bosonit.block7crud.controller.dto.PersonOutputDto;
import com.bosonit.block7crud.domain.entity.Person;
import com.bosonit.block7crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Escribimos esta etiqueta para que spring instancee esta clase e implementamos la interfaz de servicio
public class PersonServiceImp implements PersonService{
    //Bean inyectado de la clase PersonRepositoru (esta clase la creo automáticamente Spring)
    //Definimos un objeto del tipo PersonRepository, ya que ahí se encuentran los métodos del CRUD
    @Autowired
    PersonRepository personRepository;

    //CREATE
    @Override
    public PersonOutputDto addPerson(PersonInputDto person) {
        Person persona = new Person(person);           //Creamos un Person usando PersonInputDto
        PersonOutputDto personaDto = personRepository  //Usamos los métodos del repositorio
                                     .save(persona)    //Guardamos al estudiante de tipo Person
                                     .personToPersonOutputDto(); //Transformamos de Person  a PersonOutputDto
        return personaDto; //Regresamos un estudiante de tipo Dto
    }

    //READ
    /*El método findById devuelve un Optional. El método orElseThrow del objeto optional nos devuelve el dato si el optional contiene datos,
    o una excepción NoSuchElementException en caso contrario.*/
    @Override
    public PersonOutputDto getPersonById(int id) {
        Person persona = personRepository  //Usamos los métodos del repositorio
                        .findById(id)      //Revisa si existe un estudiante con ese id
                        .orElseThrow();    //Devuelve a la personaDto si existe, si no una excepción NoSuchElementException
        PersonOutputDto personaDto = persona.personToPersonOutputDto();//Transformamos de Person a PersonOutputDto

        return personaDto;
    }
    /*Las consultas que devuelvan múltiples resultados, es necesario paginarlo.*/
    @Override
    public List<PersonOutputDto> getAllPersons(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personRepository.findAll(pageRequest)
                               .getContent()
                               .stream()
                               .map(Person::personToPersonOutputDto)
                               .toList();
    }

    //UPDATE
    @Override
    public PersonOutputDto updatePerson(PersonInputDto person) {
        int id = person.getId();


        personRepository  //Usamos los métodos del repositorio
        .findById(id)     //Revisa si existe un estudiante con ese id
        .orElseThrow();   //Devuelve el estudianteDto si existe, si no una excepción NoSuchElementException

        Person personaUpdated = new Person(person); //Crea un nuevo estudiante con los parámetros de la función (actualiza datos)

        PersonOutputDto personaDto = personRepository           //Usamos los métodos del repositorio
                                    .save(personaUpdated)       //Guardamos al estudiante de tipo Person
                                    .personToPersonOutputDto(); //Transformamos de Student  a PersonOutputDto
        return personaDto;
    }

    //DELETE
    @Override
    public void deletePersonById(int id) {
        personRepository.findById(id).orElseThrow();
        personRepository.deleteById(id);
    }

}
