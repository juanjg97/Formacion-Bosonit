package com.bosonit.examen_jpa_cascade.mappers;

import com.bosonit.examen_jpa_cascade.controllers.dtos.ClienteOutput;
import com.bosonit.examen_jpa_cascade.domain.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {
    ClienteMapper mapper = Mappers.getMapper(ClienteMapper.class);

    ClienteOutput clienteToClienteOutput(Cliente cliente);
}
