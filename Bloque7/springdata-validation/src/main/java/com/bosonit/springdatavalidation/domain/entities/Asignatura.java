package com.bosonit.springdatavalidation.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "asignatura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Asignatura {
    @Id
    @GeneratedValue
    @Column(name = "id_asignatura")
    private int id_asignatura;

    //Muchas asignaturas pueden tener un estudiante
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @Column(name = "asignatura")
    private String asignatura;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(nullable = false, name = "fecha_inicio")
    private Date fecha_inicio;

    @Column(name = "fecha_termino")
    private Date fecha_termino;

}
