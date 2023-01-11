package com.bosonit.springdatavalidation.controllers;


import com.bosonit.springdatavalidation.controllers.dtos.inputs.ProfesorInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.ProfesorOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.domain.entities.Profesor;
import com.bosonit.springdatavalidation.mappers.ProfesorMapper;
import com.bosonit.springdatavalidation.repositories.PersonaRepositorio;
import com.bosonit.springdatavalidation.repositories.ProfesorRepositorio;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProfesorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    PersonaRepositorio personaRepository;
    @Autowired
    ProfesorRepositorio profesorRepository;
    @Autowired
    ObjectMapper objectMapper;

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

        System.out.println("-------------------------------Persona creada con id: "+persona.getId_usuario()+"---------------------------------------");
        return personaRepository.save(persona);
    }

    Pair<Profesor, Persona> crearEntidades() {
        Persona persona = new Persona();

        persona.setUsuario("juanjg");
        persona.setPassword("password");
        persona.setName("Juan");
        persona.setSurname("Jasso");
        persona.setCompany_email("juan@bosonitemail.com");
        persona.setPersonal_email("juan@email.com");
        persona.setCity("Toluca");
        persona.isActive();
        persona.setCreated_date(new Date(2022,11,1));
        persona.setImage_url("/image1.jpg");
        persona.setTermination_date(new Date(2022,11,2));

        Profesor profesor = new Profesor();

        profesor.setComentarios("Django");
        profesor.setRama("Backend");
        profesor.setPersona(persona);
        profesor.setStudentList(new ArrayList<>());

        persona.setProfesor(profesor);
        profesor.setPersona(persona);

        persona = personaRepository.save(persona);
        profesor = profesorRepository.save(profesor);

        return Pair.of(profesor, persona);
    }

    @AfterEach
    void eliminarObjeto() {
        personaRepository.deleteAll();
        profesorRepository.deleteAll();
    }

    @Test
    @DisplayName("1.-Agregando profesor a la bdd")
    void addProfesor() throws Exception{
        Persona persona = crearPersona();
        int persona_id = persona.getId_usuario();

        ProfesorInput profesorInput = new ProfesorInput();
        profesorInput.setComentarios("Springboot");
        profesorInput.setRama("Backend");
        profesorInput.setId_persona(persona_id);

        String path = "/profesor/add";

        this.mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(profesorInput)))
                        .andExpect(status().isCreated()).andReturn();

        // Profesor profesorActual = profesorRepository.findAll().get(0);
        Profesor profesorActual = profesorRepository.findById(persona_id).orElseThrow();

        assertTrue(profesorRepository.findAll().size() > 0);
        assertEquals(profesorInput.getRama(), profesorActual.getRama());
        assertEquals(profesorInput.getComentarios(), profesorActual.getComentarios());
    }

    @Test
    @DisplayName("2.-Agregando profesor con rama null")
    void addProfesorBranchNoValido() throws Exception{
        Persona persona = crearPersona();
        int persona_id = persona.getId_usuario();

        ProfesorInput profesorInput = new ProfesorInput();
        profesorInput.setComentarios("Springboot");
        profesorInput.setId_persona(persona_id);

        String path = "/profesor/add";

        this.mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(profesorInput)))
                        .andExpect(status().isUnprocessableEntity()).andReturn();

        assertThat(profesorRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("3.-Agregando profesor con persona null")
    void addProfesorPersonaNoValida() throws Exception{
        Persona persona = crearPersona();
        int persona_id = persona.getId_usuario();

        ProfesorInput profesorInput = new ProfesorInput();
        profesorInput.setRama("Backend");


        String path = "/profesor/add";

        this.mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(profesorInput)))
                        .andExpect(status().isNotFound()).andReturn();

        assertThat(profesorRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("4.-Obteniendo profesor id")
    void getProfesorById() throws Exception{

        Profesor profesor = crearEntidades().getFirst();
        int id_profesor = profesor.getId_profesor();

        String path = "/profesor/read/{id_profesor}";

        MvcResult mvcResult = mockMvc.perform(get(path, id_profesor))
                              .andExpect(status().isOk()).andReturn();


        ProfesorOutput profesorEsperado = ProfesorMapper.pMapper.profesorToProfesorOutput(profesor);
        ProfesorOutput profesorActual = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ProfesorOutput.class);

        profesorActual.setStudents(null);

        System.out.println(profesorEsperado.toString());
        System.out.println(profesorActual.toString());

        assertEquals(profesorEsperado.toString(), profesorActual.toString());
    }



    @Test
    @DisplayName("5.-Obteniendo todos los profesores")
    void getAllProfesores() throws Exception {
        Profesor profesor = crearEntidades().getFirst();
        crearEntidades();

        String path ="/profesor/readAll";
        MvcResult mvcResult = this.mockMvc.perform(get(path))
                .andExpect(status().isOk()).andReturn();

        String profesoresString = mvcResult.getResponse().getContentAsString();

        List<ProfesorOutput> listaProfesores = objectMapper.readValue(profesoresString, new TypeReference<>() {});

        assertTrue(listaProfesores.size() > 0);

        ProfesorOutput profesorObtenido = listaProfesores.get(0);
        ProfesorOutput profesorEsperado = ProfesorMapper.pMapper.profesorToProfesorOutput(profesor);

        assertEquals(profesorObtenido.toString(), profesorEsperado.toString());
    }

    @Test
    @DisplayName("6.-Modificando los datos de profesor por su id")
    void updateProfesorById() throws Exception {
        String path = "/profesor/update/{id_profesor}";

        Pair<Profesor,Persona> entidades = crearEntidades();

        Profesor profesor = entidades.getFirst();
        Persona persona = entidades.getSecond();

        int id_profesor = profesor.getId_profesor();
        int id_persona = persona.getId_usuario();

        ProfesorInput profesorInput = new ProfesorInput();
        profesorInput.setComentarios("Springboot");
        profesorInput.setRama("FullStack");
        profesorInput.setId_persona(id_persona);

        String comentarioEsperado1 = "Django";
        String comentarioObtenido1 = (profesorRepository.findById(id_profesor).orElseThrow()).getComentarios();

        String ramaEsperada1 = "Backend";
        String ramaObtenida1 = (profesorRepository.findById(id_profesor).orElseThrow()).getRama();

        assertEquals(comentarioEsperado1,comentarioObtenido1);
        assertEquals(ramaEsperada1,ramaObtenida1);

        this.mockMvc.perform(put(path,id_profesor)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(profesorInput)))
                        .andExpect(status().isOk()).andReturn();


        String comentarioEsperado2 = "Springboot";
        String comentarioObtenido2 = (profesorRepository.findById(1).orElseThrow()).getComentarios();

        String ramaEsperada2 = "FullStack";
        String ramaObtenida2 = (profesorRepository.findById(1).orElseThrow()).getRama();

        assertEquals(comentarioEsperado2,comentarioObtenido2);
        assertEquals(ramaEsperada2,ramaObtenida2);

    }

    @Test
    @DisplayName("7.-Borrando datos de profesor por su id ")
    void deleteProfesorById() throws Exception {

        Profesor profesor = crearEntidades().getFirst();
        int id_profesor = profesor.getId_profesor();

        String path = "/profesor/delete/{id}";
        mockMvc.perform(delete(path, id_profesor))
                .andExpect(status().isOk());

        assertThat(profesorRepository.findAll()).isEmpty();
    }

}