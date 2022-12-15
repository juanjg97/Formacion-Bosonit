package com.bosonit.springdatavalidation.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInput {
    int num_hours_week;
    String comentarios;
    String rama;
    int id_usuario;
    int id_profesor;
}
