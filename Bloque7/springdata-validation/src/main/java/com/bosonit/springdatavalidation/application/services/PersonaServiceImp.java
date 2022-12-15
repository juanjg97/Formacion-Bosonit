package com.bosonit.springdatavalidation.application.services;

import com.bosonit.springdatavalidation.controllers.dtos.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.PersonaOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import com.bosonit.springdatavalidation.exceptions.UnprocessableEntityException;
import com.bosonit.springdatavalidation.mappers.PersonaMapper;
import com.bosonit.springdatavalidation.repositories.PersonaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;


@Service
public class PersonaServiceImp implements PersonaService {
    //Inyectamos el repositorio para utilizar los métodos CRUD
    @Autowired
    PersonaRepositorio personaRepositorio; //.save(), .findById(), .findByUsuario(), findAll()

    //Los siguientes métodos reciben entidades, realiza las operaciones CRUD, regresa entidades (En el controlador hacemos las conversiones)

    //Lógica de negocio incluyendo lógica CRUD
    @Override
    public PersonaOutput addPersona(PersonaInput personaInput) throws Exception {
        //Revisa que la información de personaInput que llega de Postman esté completa, si no manda error
        checkInformationPerson(personaInput);
        //Transforma de input a entidad para usar los métodos de lógica de negocio y crud
        Persona persona = PersonaMapper.pMapper.personaInputToPersona(personaInput);

        //Agrega a la persona a la bdd, después conviertela a output
        PersonaOutput personaOutput= PersonaMapper.pMapper.personaToPersonaOutput(personaRepositorio.save(persona));



        System.out.println("Persona agregada");

        return personaOutput;
    }

    @Override
    public PersonaOutput getPersonaById(int id) {
        try {
            Persona p = personaRepositorio.findById(id).orElseThrow();
            PersonaOutput pO = PersonaMapper.pMapper.personaToPersonaOutput(p);
            return pO;
        }catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public PersonaOutput updatePersona(PersonaInput personaInput) {
        try {
            getPersonaById(personaInput.getId_usuario());
            Persona persona = PersonaMapper.pMapper.personaInputToPersona(personaInput);
            PersonaOutput personaOutput= PersonaMapper.pMapper.personaToPersonaOutput(personaRepositorio.save(persona));
            return personaOutput;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Persona no encontrada");
        }
    }

    @Override
    public PersonaOutput getPersonaByUsuario(String usuario) {
        Persona p = personaRepositorio.findByUsuario(usuario);
        return PersonaMapper.pMapper.personaToPersonaOutput(p);
    }

    @Override
    public Iterable<PersonaOutput> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Iterable <Persona> personas = personaRepositorio.findAll(pageRequest).getContent();
        Iterable<PersonaOutput> personasO = StreamSupport
                                .stream(personas.spliterator(),false)
                                .map(persona -> PersonaMapper.pMapper.personaToPersonaOutput(persona)).toList();

        return personasO;
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
    public void checkInformationPerson(PersonaInput personaInput) throws Exception {
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
