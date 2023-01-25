package com.bosonit.block16ticket.models;

import com.bosonit.block16ticket.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {
    int idViaje;
    String origin;
    String destination;
    String departureDate;
    String arrivalDate;
    List<Cliente> passengers = new ArrayList<>();
    Boolean  status;
}
