package com.bosonit.springdatavalidation.mappers;

import com.bosonit.springdatavalidation.controllers.dtos.PersonaInput;
import com.bosonit.springdatavalidation.controllers.dtos.PersonaOutput;
import com.bosonit.springdatavalidation.domain.entities.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {
    //Objeto de la clase Mapper para después usar los métodos para conversiones
    PersonaMapper pMapper = Mappers.getMapper(PersonaMapper.class);

    //Conversión de input a entidad
    Persona personaInputToPersona(PersonaInput personaInput);

    //Conversión de entidad a output
    PersonaOutput personaToPersonaOutput(Persona persona);
}
