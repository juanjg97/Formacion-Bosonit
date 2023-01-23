package com.bosonit.tripbackend.dtos;

import ch.qos.logback.core.net.server.Client;
import com.bosonit.tripbackend.domain.entities.Cliente;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ViajeInput {
    int idViaje;
    String origin;
    String destination;
    String departureDate;
    String arrivalDate;
    Boolean  status;
}
