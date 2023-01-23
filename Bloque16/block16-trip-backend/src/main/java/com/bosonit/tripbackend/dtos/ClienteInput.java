package com.bosonit.tripbackend.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteInput {
    int idCliente;
    String nombre;
    String apellido;
    int edad;
    String email;
    String teléfono;
}
