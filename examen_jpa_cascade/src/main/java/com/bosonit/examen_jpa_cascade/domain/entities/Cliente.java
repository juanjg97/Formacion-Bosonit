package com.bosonit.examen_jpa_cascade.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
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
}
