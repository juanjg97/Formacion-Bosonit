package com.bosonit.springdatavalidation.mappers;

import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaInput;
import com.bosonit.springdatavalidation.controllers.dtos.AsignaturaOutput;
import com.bosonit.springdatavalidation.domain.entities.Asignatura;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AsignaturaMapper {
    AsignaturaMapper aMapper = Mappers.getMapper(AsignaturaMapper.class);

    Asignatura asignaturaInputToAsignatura(AsignaturaInput asignaturaInput);
    AsignaturaOutput asignaturaToAsignaturaOutput(Asignatura asignatura);

}
