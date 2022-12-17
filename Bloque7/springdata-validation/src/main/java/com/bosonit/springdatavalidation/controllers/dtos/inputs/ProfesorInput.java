package com.bosonit.springdatavalidation.controllers.dtos.inputs;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProfesorInput {
    String comentarios;
    String rama;
    int id_persona;
}
