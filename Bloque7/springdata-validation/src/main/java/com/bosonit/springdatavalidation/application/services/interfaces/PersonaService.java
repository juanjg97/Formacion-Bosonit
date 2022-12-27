package com.bosonit.springdatavalidation.application.services.interfaces;

import com.bosonit.springdatavalidation.controllers.dtos.inputs.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.PersonaOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface PersonaService {
    //Métodos para implementar la lógica de negocio más el CRUD
    PersonaOutput addPersona(PersonaInput personaInput) throws Exception; //Después usará .save()
    PersonaOutput updatePersona(PersonaInput personaInput);
    PersonaOutput getPersonaById(int id); //Después usará .findById()
    PersonaOutput getPersonaById2(int id); //Después usará .findById()

    //Implementará método declarado en el repositorio
    PersonaOutput getPersonaByUsuario(String usuario); //Después usará findByUsuario() => Método personalizado

    Iterable<PersonaOutput> getAllPersonas(int pageNumber, int pageSize); //Después usará findAll()

    //Método para validar el ingreso de los datos de InputPersona
    void checkInformationPerson(PersonaInput personaInput) throws Exception;
    //Método para elimiar personas por medio de su id
    void deletePersona(int id_usuario);
    // Métodos bloque 12
    List<PersonaOutput> personByFields(String usuario, String name, String surname, Date created_date, String orderBy) throws Exception;
    Page<PersonaOutput> searchAllWithPagination(int offset, int pageSize);

}
