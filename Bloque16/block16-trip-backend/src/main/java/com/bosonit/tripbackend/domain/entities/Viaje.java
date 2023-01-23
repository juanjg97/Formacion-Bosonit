package com.bosonit.tripbackend.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "viaje")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idViaje")
    int idViaje;

    @Column(name = "origin")
    String origin;

    @Column(name = "destination")
    String destination;

    @Column(name = "departureDate")
    String departureDate;

    @Column(name = "arrivalDate")
    String arrivalDate;

    @OneToMany
    List<Cliente> passengers = new ArrayList<>();

    @Column(name = "status")
    Boolean  status;
}
