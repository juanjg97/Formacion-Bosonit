package com.bosonit.block11uploaddownloadfiles.application.services;

import com.bosonit.block11uploaddownloadfiles.DTOs.ArchivoInput;
import com.bosonit.block11uploaddownloadfiles.DTOs.ArchivoOutput;

public interface ArchivoService {
    ArchivoOutput addMetadatos(ArchivoInput datosFicheroInputDto);
    ArchivoOutput getDatosFicheroById(int id);
}
