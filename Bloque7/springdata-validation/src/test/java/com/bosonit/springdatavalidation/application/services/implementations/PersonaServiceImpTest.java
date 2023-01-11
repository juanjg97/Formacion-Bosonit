package com.bosonit.springdatavalidation.application.services.implementations;

import com.bosonit.springdatavalidation.controllers.dtos.inputs.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.PersonaOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.exceptions.EntityNotFoundException;
import com.bosonit.springdatavalidation.exceptions.UnprocessableEntityException;
import com.bosonit.springdatavalidation.mappers.PersonaMapper;
import com.bosonit.springdatavalidation.repositories.PersonaRepositorio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonaServiceImpTest {
    @Mock
    PersonaRepositorio personaRepository;
    @InjectMocks
    PersonaServiceImp personaService;

    Persona crearPersona(){
        Persona persona= new Persona();

        persona.setId_usuario(1);
        persona.setUsuario("juanjg");
        persona.setPassword("password");
        persona.setName("Juan");
        persona.setSurname("Jasso");
        persona.setCompany_email("juan@mail.com");
        persona.setPersonal_email("juan@bosonit.com");
        persona.setCity("Toluca");
        persona.isActive();
        persona.setCreated_date(new Date(2022,11,1));
        persona.setImage_url("image/a");
        persona.setTermination_date(new Date(2042,11,2));

        return persona;
    }

    PersonaInput crearPersonaInput() {
        PersonaInput personaInput = new PersonaInput();

        personaInput.setId_usuario(1);
        personaInput.setUsuario("eter.eos");
        personaInput.setPassword("1234");
        personaInput.setName("Fab");
        personaInput.setSurname("Almazan");
        personaInput.setCompany_email("fab@email.com");
        personaInput.setPersonal_email("fabal@email.com");
        personaInput.setCity("edomex");
        personaInput.isActive();
        personaInput.setCreated_date(new Date(2022,7,1));
        personaInput.setImage_url("/image1.jpg");
        personaInput.setTermination_date(new Date(2022,10,1));
        return personaInput;
    }

    @AfterEach
    void eliminarObjeto() {
        personaRepository.deleteAll();
    }

    @Test
    @DisplayName("1.-Agrega una persona a la BDD")
    void addPersona() throws Exception {
        PersonaInput personaInput = crearPersonaInput();
        Persona persona = PersonaMapper.pMapper.personaInputToPersona(personaInput);

        when(personaRepository.save(any(Persona.class))).thenReturn(persona);

        PersonaOutput personaActual = personaService.addPersona(personaInput);

        int id_esperado = persona.getId_usuario();
        int id_actual = personaActual.getId_usuario();

        assertEquals(id_esperado,id_actual);
        verify(personaRepository, atLeastOnce()).save(any(Persona.class));
    }

    @Test
    @DisplayName("2.-Agregar persona con datos no validos")
    void addPersonaNoValida() {
        PersonaInput personaInput = crearPersonaInput();
        personaInput.setCreated_date(null);

        UnprocessableEntityException excepcionEsperada = new UnprocessableEntityException("created_date no puede ser nulo");
        UnprocessableEntityException excepcionObtenida = assertThrows(UnprocessableEntityException.class,
                () -> {personaService.addPersona(personaInput);});

        assertEquals(excepcionEsperada.getMessage(), excepcionObtenida.getMessage());
    }

    @Test
    @DisplayName("3.-Obteniendo persona por id")
    void getPersonaById() {
        Persona persona = crearPersona();
        int id_persona = persona.getId_usuario();

        when(personaRepository.findById(id_persona)).thenReturn(Optional.of(persona));

        PersonaOutput personaActual = personaService.getPersonaById(id_persona);

        PersonaOutput personaEsperada = PersonaMapper.pMapper.personaToPersonaOutput(persona);

        assertEquals(personaEsperada.toString(), personaActual.toString());
        verify(personaRepository, atLeastOnce()).findById(id_persona);
    }

    @Test
    @DisplayName("4.-Buscando una persona que no existe")
    void getPersonaByIdNotFound() {
        int idPersona = 1;
        EntityNotFoundException excepcionEsperada = new EntityNotFoundException("No se encontró el id: " + idPersona);

        when(personaRepository.findById(idPersona)).thenReturn(Optional.empty());

        EntityNotFoundException exceptionObtenida = assertThrows(EntityNotFoundException.class, () -> personaService.getPersonaById(idPersona));
        assertEquals(excepcionEsperada.getMessage(), exceptionObtenida.getMessage());
    }

    @Test
    @DisplayName("5.-Obteniendo los datos de una persona por su usuario")
    void getPersonaByUsuario() {
        Persona persona = crearPersona();
        PersonaOutput personaEsperado = PersonaMapper.pMapper.personaToPersonaOutput(persona);

        when(personaRepository.findByUsuario(persona.getUsuario())).thenReturn(persona);

        PersonaOutput personaObtenido = assertDoesNotThrow(() -> personaService.getPersonaByUsuario(persona.getUsuario()));
        assertEquals(personaEsperado.toString(), personaObtenido.toString());
        verify(personaRepository, atLeastOnce()).findByUsuario(personaEsperado.getUsuario());
    }

    @Test
    @DisplayName("6.- Actualizar persona por id")
    void updatePersonaById() {
        Persona persona = crearPersona();
        int id_persona = persona.getId_usuario();

        PersonaInput personaInput = crearPersonaInput();
        Persona personaActualizada = PersonaMapper.pMapper.personaInputToPersona(personaInput);

        assertNotEquals(persona,personaActualizada);

        when(personaRepository.findById(id_persona)).thenReturn(Optional.of(persona));
        when(personaRepository.save(any(Persona.class))).thenReturn(persona);

        PersonaOutput personaEsperada = PersonaMapper.pMapper.personaToPersonaOutput(personaActualizada);
        PersonaOutput personaActual = personaService.updatePersona(personaInput);

        assertEquals(personaEsperada.toString(), personaActual.toString());
        verify(personaRepository, atLeastOnce()).findById(persona.getId_usuario());
        verify(personaRepository, atLeastOnce()).save(any(Persona.class));

    }

    @Test
    @DisplayName("7.-Eliminar persona por  id")
    void deletePersonaById() {
        Persona persona = crearPersona();
        int persona_id = persona.getId_usuario();

        when(personaRepository.findById(persona_id)).thenReturn(Optional.of(persona));
        doNothing().when(personaRepository).deleteById(persona_id);

        personaService.deletePersona(persona_id);

        verify(personaRepository, atLeastOnce()).findById(persona.getId_usuario());
        verify(personaRepository, atLeastOnce()).deleteById(persona.getId_usuario());

    }
}