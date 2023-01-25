package com.bosonit.block16ticket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    int idCliente;
    String nombre;
    String apellido;
    int edad;
    String email;
    String teléfono;
}
