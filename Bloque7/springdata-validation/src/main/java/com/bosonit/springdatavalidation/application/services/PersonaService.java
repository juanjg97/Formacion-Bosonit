package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.PersonaInput;
import com.bosonit.springdatavalidation.domain.entities.Persona;

public interface PersonaService {
    //Métodos para implementar la lógica de negocio más el CRUD
    Persona addPersona(Persona persona); //Después usará .save()
    Persona updatePersona(Persona persona);
    Persona getPersonaById(int id); //Después usará .findById()

    //Implementará método declarado en el repositorio
    Persona getPersonaByUsuario(String usuario); //Después usará findByUsuario() => Método personalizado

    Iterable<Persona> getAllPersonas(int pageNumber, int pageSize); //Después usará findAll()

    //Método para validar el ingreso de los datos de InputPersona
    void checkInformation(PersonaInput personaInput) throws Exception;
    //Método para elimiar personas por medio de su id
    void deletePersona(int id_usuario);

}
