package com.bosonit.springdatavalidation.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profesor {
    @Id
    @GeneratedValue()
    @Column(name = "id_profesor")
    private int id_profesor;

    //Un profesor puede ser sólo 1 persona
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    //Un profesor  puede tener muchos estudiantes
    @OneToMany(mappedBy = "profesor")
    private List<Student> studentList = new ArrayList<>();

    @Column(name = "comentarios")
    private String comentarios;

    @Column(nullable = false, name = "rama")
    private String rama;


}
