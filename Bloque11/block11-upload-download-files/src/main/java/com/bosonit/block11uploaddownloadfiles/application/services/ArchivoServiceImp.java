package com.bosonit.block11uploaddownloadfiles.application.services;

import com.bosonit.block11uploaddownloadfiles.DTOs.ArchivoInput;
import com.bosonit.block11uploaddownloadfiles.DTOs.ArchivoOutput;
import com.bosonit.block11uploaddownloadfiles.Mappers.ArchivoMapper;
import com.bosonit.block11uploaddownloadfiles.domain.entities.Archivo;
import com.bosonit.block11uploaddownloadfiles.exceptions.EntityNotFoundException;
import com.bosonit.block11uploaddownloadfiles.repositories.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ArchivoServiceImp implements ArchivoService{
    @Autowired
    ArchivoRepository archivoRepository;

    @Override
    public ArchivoOutput getDatosFicheroById(int id) {
        try {
            Archivo datos_archivo = archivoRepository.findById(id).orElseThrow();
            return ArchivoMapper.aMapper.archivoToArchivoOutput(datos_archivo);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No se encontró el id: " + id);
        }
    }

    @Override
    public ArchivoOutput addMetadatos(ArchivoInput archivoInput) {
        Archivo datos_archivo = ArchivoMapper.aMapper.archivoInputToArchivo(archivoInput);
        archivoRepository.save(datos_archivo);
        return ArchivoMapper.aMapper.archivoToArchivoOutput(datos_archivo);

    }
}
