package com.bosonit.examen_jpa_cascade.mappers;

import com.bosonit.examen_jpa_cascade.controllers.dtos.ClienteOutput;
import com.bosonit.examen_jpa_cascade.domain.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//Interfaz para conversiones entre entidades y dtos
@Mapper
public interface ClienteMapper {
    //Creamos objeto de la clase Mapper
    ClienteMapper cMapper = Mappers.getMapper(ClienteMapper.class);

    //Definimos método para convertir de entidad a output
    ClienteOutput clienteToClienteOutput(Cliente cliente);
}
