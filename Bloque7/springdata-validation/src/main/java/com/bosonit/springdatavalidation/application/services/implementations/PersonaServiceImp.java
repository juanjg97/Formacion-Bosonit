package com.bosonit.springdatavalidation.application.services.implementations;

import com.bosonit.springdatavalidation.application.services.interfaces.PersonaService;
import com.bosonit.springdatavalidation.controllers.dtos.inputs.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.PersonaOutput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs_full.PersonaFullOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import com.bosonit.springdatavalidation.exceptions.UnprocessableEntityException;
import com.bosonit.springdatavalidation.mappers.PersonaMapper;
import com.bosonit.springdatavalidation.repositories.PersonaRepositorio;

import com.bosonit.springdatavalidation.repositories.ProfesorRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.StreamSupport;


@Service
@AllArgsConstructor
@Slf4j
public class PersonaServiceImp implements PersonaService {
    @Autowired
    PersonaRepositorio personaRepositorio; //.save(), .findById(), .findByUsuario(), findAll()

    @Autowired
    private ProfesorRepositorio profesorRepositorio;

    @PersistenceContext
    private EntityManager entityManager;

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
    public PersonaFullOutput getPersonaById2(int id) {
        try {
            Persona p = personaRepositorio.findById(id).orElseThrow();
            return p.personaToPersonaFullOutput();
        }catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public PersonaOutput updatePersona(PersonaInput personaInput) {
        try {
            Persona p = personaRepositorio.findById(personaInput.getId_usuario()).orElseThrow();

            // int id_usuario;
            p.setUsuario(personaInput.getUsuario());// String usuario;
            p.setPassword(personaInput.getPassword());// String password;
            p.setName(personaInput.getName());// String name;
            p.setSurname(personaInput.getSurname());// String surname;
            p.setCompany_email(personaInput.getCompany_email());// String company_email;
            p.setPersonal_email(personaInput.getPersonal_email());// String personal_email;
            p.setCity(personaInput.getCity());// String city;
            p.setActive(personaInput.isActive());// boolean active;
            p.setCreated_date(personaInput.getCreated_date());// Date created_date;
            p.setImage_url(personaInput.getImage_url());// String image_url;
            p.setTermination_date(personaInput.getTermination_date());// Date termination_date;

            PersonaOutput personaOutput= PersonaMapper.pMapper.personaToPersonaOutput(personaRepositorio.save(p));

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

    @Override
    public List<PersonaOutput> personByFields(String usuario, String name, String surname, Date created_date, String order_by) throws Exception {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Persona> query = criteriaBuilder.createQuery(Persona.class);

        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        if(usuario != null) {
            predicates.add(criteriaBuilder.like(root.get("usuario"), usuario));
        }
        if(name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), name));
        }
        if(surname != null) {
            predicates.add(criteriaBuilder.like(root.get("surname"), surname));
        }
        if(created_date != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get("created_date"), created_date));
        }

        Predicate[] sentencia = predicates.toArray(new Predicate[predicates.size()]);

        query.select(root).where(sentencia);

        if(order_by.equals("usuario")){
            query.orderBy(criteriaBuilder.asc(root.get("usuario")));
        }else if(order_by.equals("name")){
            query.orderBy(criteriaBuilder.asc(root.get("name")));
        }else{
            throw new Exception("Debe especificar un campo");
        }

        List<Persona> personas = entityManager.createQuery(query).getResultList().stream().toList();

        List<PersonaOutput> personasOutput= personas.stream().map(persona -> PersonaMapper.pMapper.personaToPersonaOutput(persona)).toList();

        return personasOutput;
    }

    @Override
    public Page<PersonaOutput> searchAllWithPagination(int offset, int pageSize) {
        Page<Persona> personas = personaRepositorio.findAll(PageRequest.of(offset, pageSize));
        Page<PersonaOutput> personasOutput= personas.map(persona -> PersonaMapper.pMapper.personaToPersonaOutput(persona));

        return personasOutput;
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
