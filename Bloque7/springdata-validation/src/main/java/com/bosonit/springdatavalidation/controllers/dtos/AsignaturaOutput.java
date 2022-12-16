package com.bosonit.springdatavalidation.controllers.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AsignaturaOutput {
    private int id_asignatura;
    private String asignatura;
    private String comentarios;
    private Date fecha_inicio;
    private Date fecha_termino;
}
