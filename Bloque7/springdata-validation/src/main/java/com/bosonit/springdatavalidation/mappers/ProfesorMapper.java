package com.bosonit.springdatavalidation.mappers;

import com.bosonit.springdatavalidation.controllers.dtos.ProfesorInput;
import com.bosonit.springdatavalidation.controllers.dtos.ProfesorOutput;
import com.bosonit.springdatavalidation.domain.entities.Profesor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfesorMapper {
    ProfesorMapper pMapper = Mappers.getMapper(ProfesorMapper.class);

    Profesor profesorInputToProfesor(ProfesorInput profesorInput);
    ProfesorOutput profesorToProfesorOutput(Profesor profesor);
}
