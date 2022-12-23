package com.bosonit.block11uploaddownloadfiles.Mappers;

import com.bosonit.block11uploaddownloadfiles.DTOs.ArchivoInput;
import com.bosonit.block11uploaddownloadfiles.DTOs.ArchivoOutput;
import com.bosonit.block11uploaddownloadfiles.domain.entities.Archivo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArchivoMapper {
    ArchivoMapper aMapper = Mappers.getMapper(ArchivoMapper.class);

    Archivo archivoInputToArchivo(ArchivoInput archivoInput);
    ArchivoOutput archivoToArchivoOutput(Archivo archivo);
}
