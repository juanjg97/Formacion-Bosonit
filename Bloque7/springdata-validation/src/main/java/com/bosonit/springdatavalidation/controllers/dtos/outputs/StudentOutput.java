package com.bosonit.springdatavalidation.controllers.dtos.outputs;

import com.bosonit.springdatavalidation.controllers.dtos.outputs.AsignaturaOutput;
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
