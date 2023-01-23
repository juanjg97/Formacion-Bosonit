package com.bosonit.tripbackend.Mappers;


import com.bosonit.tripbackend.domain.entities.Cliente;
import com.bosonit.tripbackend.dtos.ClienteInput;
import com.bosonit.tripbackend.dtos.ClienteOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses=ViajeMapper.class)
public interface ClienteMapper {
    ClienteMapper cMapper = Mappers.getMapper(ClienteMapper.class);

    Cliente ClienteInputToCliente(ClienteInput clienteInput);
    ClienteOutput ClienteToClienteOutput(Cliente cliente);
}
