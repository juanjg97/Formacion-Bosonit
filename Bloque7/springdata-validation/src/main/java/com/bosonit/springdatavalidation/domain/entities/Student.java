package com.bosonit.springdatavalidation.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "id_student")
    private int id_student;

    //Un estudiante puede ser sólo 1 persona
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Persona persona;

    //Muchos estudiantes pueden tener sólo 1 profesor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;

    //Un estudiante puede tener muchas asignaturas
    @OneToMany(mappedBy = "student")
    private List<Asignatura> asignaturaList = new ArrayList<>();

    @Column(nullable = false, name = "num_hours_week")
    private int num_hours_week;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(nullable = false, name = "rama")
    private String rama;


}
