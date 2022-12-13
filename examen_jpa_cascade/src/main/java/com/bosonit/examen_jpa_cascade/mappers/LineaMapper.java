package com.bosonit.examen_jpa_cascade.mappers;

import com.bosonit.examen_jpa_cascade.controllers.dtos.LineaInput;
import com.bosonit.examen_jpa_cascade.controllers.dtos.LineaOutput;
import com.bosonit.examen_jpa_cascade.domain.entities.Linea;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//Interfaz para conversiones entre entidades y dtos
@Mapper
public interface LineaMapper {
    //Creamos objeto de la clase Mapper
    LineaMapper lMapper = Mappers.getMapper(LineaMapper.class);

    //Definimos métodos para convertir de entidad a output
    Linea lineaInputToLinea(LineaInput lineaInput);
    LineaOutput lineaToLineaOutput(Linea linea);
}
