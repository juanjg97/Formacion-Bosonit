package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.PersonaInput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import com.bosonit.springdatavalidation.exceptions.UnprocessableEntityException;
import com.bosonit.springdatavalidation.repositories.PersonaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


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
        try {
            return personaRepositorio.findById(id).orElseThrow();
        }catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public Persona updatePersona(Persona persona) {
        try {
            Persona p = getPersonaById(persona.getId_usuario());
            return p;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Persona no encontrada");
        }
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

    @Override
    public void deletePersona(int id_usuario) {
        try {
            personaRepositorio.findById(id_usuario).orElseThrow();
            personaRepositorio.deleteById(id_usuario);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }


    //Método para validar la información enviada por Postman, en este caso recibe directamente el input
    @Override
    public void checkInformation(PersonaInput personaInput) throws Exception {
        if (personaInput.getUsuario() == null) {
            throw new UnprocessableEntityException("Usuario no puede ser nulo");
        } else if (personaInput.getUsuario().length() > 10) {
            throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres");
        } else if (personaInput.getPassword() == null) {
            throw new UnprocessableEntityException("Password no puede ser nulo");
        } else if (personaInput.getName() == null) {
            throw new UnprocessableEntityException("Name no puede ser nulo");
        } else if (personaInput.getCompany_email() == null) {
            throw new UnprocessableEntityException("company_email no puede ser nulo");
        } else if (personaInput.getPersonal_email() == null) {
            throw new UnprocessableEntityException("personal_email no puede ser nulo");
        } else if (personaInput.getCity() == null) {
            throw new UnprocessableEntityException("city no puede ser nulo");
        } else if (personaInput.getCreated_date() == null) {
            throw new UnprocessableEntityException("created_date no puede ser nulo");
        }
    }
}
