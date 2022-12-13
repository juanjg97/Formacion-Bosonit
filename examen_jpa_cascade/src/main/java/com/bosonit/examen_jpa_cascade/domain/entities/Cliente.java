package com.bosonit.examen_jpa_cascade.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Cliente {
    @Id
    @GeneratedValue
    @Column(name="id_cliente")
    int id_cliente;

    @Column(nullable = false)
    String nombre;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    List<Factura> Facturas = new ArrayList<>();

    public Cliente(String nombre) {
        this.nombre = nombre;
    }
}
