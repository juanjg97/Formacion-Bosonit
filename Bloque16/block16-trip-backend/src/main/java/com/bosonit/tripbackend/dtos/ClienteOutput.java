package com.bosonit.tripbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteOutput {
    int idCliente;
    String nombre;
    String apellido;
    int edad;
    String email;
    String teléfono;
}
