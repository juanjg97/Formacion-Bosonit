package com.bosonit.examen_jpa_cascade.controllers.dtos;

import jakarta.persistence.Entity;
import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClienteInput {
    String nombre;
}
