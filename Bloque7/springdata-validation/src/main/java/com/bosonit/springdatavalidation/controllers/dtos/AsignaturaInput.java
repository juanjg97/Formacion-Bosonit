package com.bosonit.springdatavalidation.controllers.dtos;

import com.bosonit.springdatavalidation.domain.entities.Student;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaInput {

    private int id_asignatura;
    private String asignatura;
    private String comentarios;
    private Date fecha_inicio;
    private Date fecha_termino;
    private int id_student;
}
