package com.bosonit.block11uploaddownloadfiles.DTOs;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ArchivoOutput {
    private int id_archivo;
    private String nombre_archivo;
    private Date fecha_subida;
}
