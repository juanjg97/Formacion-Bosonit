package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.PersonaInput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.repositories.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImp implements PersonaService {
    //Inyectamos el repositorio para utilizar los métodos CRUD
    @Autowired
    PersonaRepositorio personaRepositorio; //.save(), .findById(), .findByUsuario(), findAll()

    //Los siguientes métodos reciben entidades, realiza las operaciones CRUD, regresa entidades (En el controlador hacemos las conversiones)

    //Lógica de negocio incluyendo lógica CRUD
    @Override
    public Persona addPersona(Persona persona) {
        return personaRepositorio.save(persona);
    }

    @Override
    public Persona getPersonaById(int id) {
       return personaRepositorio.findById(id).orElseThrow();
    }

    @Override
    public Persona getPersonaByUsuario(String usuario) {
        return personaRepositorio.findByUsuario(usuario);
    }

    @Override
    public Iterable<Persona> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        return personaRepositorio.findAll(pageRequest).getContent();
    }

    //Método para validar la información enviada por Postman, en este caso recibe directamente el input
    @Override
    public void checkInformation(PersonaInput personaInput) throws Exception {
        if (personaInput.getUsuario() == null) {
            throw new Exception("Usuario no puede ser nulo");
        } else if (personaInput.getUsuario().length() > 10) {
            throw new Exception("Longitud de usuario no puede ser superior a 10 caracteres");
        } else if (personaInput.getPassword() == null) {
            throw new Exception("Password no puede ser nulo");
        } else if (personaInput.getName() == null) {
            throw new Exception("Name no puede ser nulo");
        } else if (personaInput.getCompany_email() == null) {
            throw new Exception("company_email no puede ser nulo");
        } else if (personaInput.getPersonal_email() == null) {
            throw new Exception("personal_email no puede ser nulo");
        } else if (personaInput.getCity() == null) {
            throw new Exception("city no puede ser nulo");
        } else if (personaInput.getCreated_date() == null) {
            throw new Exception("created_date no puede ser nulo");
        }
    }
}
