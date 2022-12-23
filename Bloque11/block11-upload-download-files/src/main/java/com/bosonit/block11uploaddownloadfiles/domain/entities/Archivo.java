package com.bosonit.block11uploaddownloadfiles.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Archivo {
    @Id
    @GeneratedValue
    private int id_archivo;
    private String nombre_archivo;
    private Date fecha_subida;
}
