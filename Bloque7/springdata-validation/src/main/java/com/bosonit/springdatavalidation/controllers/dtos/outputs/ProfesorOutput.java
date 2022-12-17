package com.bosonit.springdatavalidation.controllers.dtos.outputs;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfesorOutput {
    int id_profesor;
    String comentarios;
    String rama;
    List<StudentOutput> students;
}
