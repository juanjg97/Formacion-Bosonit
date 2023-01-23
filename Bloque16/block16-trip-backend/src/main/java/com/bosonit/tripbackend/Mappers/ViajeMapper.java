package com.bosonit.tripbackend.Mappers;

import com.bosonit.tripbackend.domain.entities.Viaje;
import com.bosonit.tripbackend.dtos.ViajeInput;
import com.bosonit.tripbackend.dtos.ViajeOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses=ClienteMapper.class)
public interface ViajeMapper {
    ViajeMapper vMapper = Mappers.getMapper(ViajeMapper.class);
    Viaje ViajeInputToViaje(ViajeInput ViajeInput);
    ViajeOutput ViajeToViajeOutput(Viaje Viaje);
}
