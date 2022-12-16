package com.bosonit.springdatavalidation.controllers.dtos;

import com.bosonit.springdatavalidation.domain.entities.Persona;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentOutput {
    int id_student;
    int num_hours_week;
    String comentarios;
    String rama;
    List<AsignaturaOutput> asignaturas;

}
