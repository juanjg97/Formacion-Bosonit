package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.PersonaOutput;

public interface PersonaService {
    //Métodos para implementar la lógica de negocio más el CRUD
    PersonaOutput addPersona(PersonaInput personaInput) throws Exception; //Después usará .save()
    PersonaOutput updatePersona(PersonaInput personaInput);
    PersonaOutput getPersonaById(int id); //Después usará .findById()

    //Implementará método declarado en el repositorio
    PersonaOutput getPersonaByUsuario(String usuario); //Después usará findByUsuario() => Método personalizado

    Iterable<PersonaOutput> getAllPersonas(int pageNumber, int pageSize); //Después usará findAll()

    //Método para validar el ingreso de los datos de InputPersona
    void checkInformationPerson(PersonaInput personaInput) throws Exception;
    //Método para elimiar personas por medio de su id
    void deletePersona(int id_usuario);

}
