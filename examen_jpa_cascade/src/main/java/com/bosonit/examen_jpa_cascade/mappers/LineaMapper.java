package com.bosonit.examen_jpa_cascade.mappers;

import com.bosonit.examen_jpa_cascade.controllers.dtos.LineaInput;
import com.bosonit.examen_jpa_cascade.controllers.dtos.LineaOutput;
import com.bosonit.examen_jpa_cascade.domain.entities.Linea;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LineaMapper {
    LineaMapper mapper = Mappers.getMapper(LineaMapper.class);

    Linea lineaInputToLinea(LineaInput lineaInput);
    LineaOutput lineaToLineaOutput(Linea linea);
}
