package com.bosonit.block11uploaddownloadfiles.DTOs;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ArchivoInput {
    private String nombre_archivo;
    private Date fecha_subida;

}
