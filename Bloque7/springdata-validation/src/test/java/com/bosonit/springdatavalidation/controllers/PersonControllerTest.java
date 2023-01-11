package com.bosonit.springdatavalidation.controllers;

import com.bosonit.springdatavalidation.controllers.dtos.inputs.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.outputs.PersonaOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import com.bosonit.springdatavalidation.mappers.PersonaMapper;
import com.bosonit.springdatavalidation.repositories.PersonaRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    PersonaRepositorio personaRepository;
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

    @AfterEach
    void eliminarPersona() {
        personaRepository.deleteAll();
    }


    @Test
    @DisplayName("1.-Agrega una persona a la BDD")
    void addPersona() throws Exception{
        PersonaInput personaInput = new PersonaInput();

        personaInput.setUsuario("juanjg");
        personaInput.setPassword("password");
        personaInput.setName("Juan");
        personaInput.setSurname("Jasso");
        personaInput.setCompany_email("juan@mail.com");
        personaInput.setPersonal_email("juan@bosonit.com");
        personaInput.setCity("Toluca");
        personaInput.isActive();
        personaInput.setCreated_date(new Date(2022,11,1));
        personaInput.setImage_url("image/a");
        personaInput.setTermination_date(new Date(2042,11,2));

        String path = "/addperson";

        MvcResult mvcResult = this.mockMvc
                        .perform(post(path).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(personaInput)))
                        .andExpect(status().isCreated()).andReturn();

        int database_size = personaRepository.findAll().size();

        assertThat(database_size).isOne();
        assertEquals(1,database_size);
    }

    @Test
    @DisplayName("2.-Actualizando persona por id")
    void modifyPersonaById() throws Exception {
        Persona persona = crearPersona();
        persona.setId_usuario(1);
        personaRepository.save(persona);

        PersonaInput personaInput = new PersonaInput();

        Date created_date = new Date(2022,11,1);
        Date termination_date = new Date(2022,11,1);

        personaInput.setId_usuario(1);
        personaInput.setUsuario("DianaG");
        personaInput.setPassword("abcd");
        personaInput.setName("Diana");
        personaInput.setSurname("Gonzalez");
        personaInput.setCompany_email("diana@bosonit.com");
        personaInput.setPersonal_email("pdiana@email.com");
        personaInput.setCity("Qro");
        personaInput.isActive();
        personaInput.setCreated_date(created_date);
        personaInput.setImage_url("image/a");
        personaInput.setTermination_date(termination_date);

        Persona personaBeforeUpdate = personaRepository.findById(persona.getId_usuario()).orElseThrow();

        System.out.println(" ==> Persona antes de actualizar: "+personaBeforeUpdate);

        String path = "/updatePersona";

        MvcResult mvcResult = this.mockMvc
                .perform(put(path, personaInput)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personaInput)))
                .andExpect(status().isOk()).andReturn();

        Persona personaAfterUpdate = personaRepository.findById(persona.getId_usuario()).get();
        personaAfterUpdate.setCreated_date(created_date); personaAfterUpdate.setTermination_date(termination_date);

        System.out.println("==> Persona después de actualizar: "+personaAfterUpdate);

        Persona personaEsperado = PersonaMapper.pMapper.personaInputToPersona(personaInput);
        Persona personaActual = personaAfterUpdate;

        assertEquals(personaEsperado.toString(), personaActual.toString());
    }

        @Test
        @DisplayName("3.-Obteniendo personas por id")
        void getPersonaById() throws Exception {
            Persona persona = crearPersona();

            String path = "/id/{id}";

            MvcResult mvcResult = this.mockMvc
                    .perform(get(path, persona.getId_usuario()))
                    .andExpect(status().isOk()).andReturn();

            PersonaOutput personaOutput = PersonaMapper.pMapper.personaToPersonaOutput(persona);
            String personaEsperado = objectMapper.writeValueAsString(personaOutput);

            String personaActual = mvcResult.getResponse().getContentAsString();

            assertEquals(personaEsperado, personaActual);
        }

       @Test
       @DisplayName("4.-Obteniendo persona nombre de usuario")
        void getPersonaByUsuario() throws Exception {
            Persona persona = crearPersona();
            PersonaOutput personaOutput = PersonaMapper.pMapper.personaToPersonaOutput(persona);

            MvcResult mvcResult = this.mockMvc
                    .perform(get("/usuario/{usuario}", persona.getUsuario()))
                    .andExpect(status().isOk()).andReturn();

            String personaEsperado = objectMapper.writeValueAsString(personaOutput);
            String personaObtenido = mvcResult.getResponse().getContentAsString();

            assertEquals(personaEsperado, personaObtenido);
        }


            @Test
            @DisplayName("5.-Obteniendo todas las personas")
            void getAllPersonas() throws Exception {
                String path = "/getall";

                crearPersona();
                crearPersona();

                MvcResult mvcResult = this.mockMvc
                        .perform(get(path))
                        .andExpect(status().isOk()).andReturn();

                String resultado = mvcResult.getResponse().getContentAsString();

                System.out.println(resultado);

                Persona[] listaPersonas = objectMapper.readValue(resultado, Persona[].class);

                assertEquals(2,listaPersonas.length);
                assertTrue(listaPersonas.length > 0);
            }




    @Test
    @DisplayName("6.-Eliminando persona por id")
    void deletePersonaById() throws Exception {
        Persona persona = crearPersona();
        //persona.setId_usuario(1);
        int persona_id = persona.getId_usuario();
        String path = "/delete/{id}";

       // System.out.println("------------------->"+persona_id);

        this.mockMvc.perform(delete(path, persona_id))
                    .andExpect(status().isOk());

        assertThat(personaRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("7.-Obteniendo persona por diferentes campos")
    void getPersonaByFields() throws Exception {
        Persona persona = crearPersona();

        String path = "/person/fields";

        MvcResult mvcResult = this.mockMvc.perform(get(path)
                        .param("usuario", persona.getUsuario())
                        .param("name", persona.getName())
                        .param("surname", persona.getSurname()))
                        .andExpect(status().isOk()).andReturn();

        String resultado = mvcResult.getResponse().getContentAsString();
        PersonaOutput[] listaPersonas = objectMapper.readValue(resultado, PersonaOutput[].class);

        String personaObtenidoJson = objectMapper.writeValueAsString(listaPersonas[0]);
        String personaEsperadoJson = objectMapper.writeValueAsString(PersonaMapper.pMapper.personaToPersonaOutput(persona));

        assertTrue(listaPersonas.length > 0);
        assertEquals(personaObtenidoJson, personaEsperadoJson);
    }
}