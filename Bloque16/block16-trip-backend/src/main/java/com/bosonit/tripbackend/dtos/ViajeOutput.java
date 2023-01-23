package com.bosonit.tripbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ViajeOutput {
    int idViaje;
    String origin;
    String destination;
    String departureDate;
    String arrivalDate;
    String  passenger;
    String  status;
}
