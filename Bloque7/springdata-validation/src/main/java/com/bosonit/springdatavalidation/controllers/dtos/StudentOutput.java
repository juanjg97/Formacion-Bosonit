package com.bosonit.springdatavalidation.controllers.dtos;

import com.bosonit.springdatavalidation.domain.entities.Persona;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentOutput {
    int id_student;
    int num_hours_week;
    String comentarios;
    String rama;
    List<AsignaturaOutput> asignaturas;
    /*
    public StudentOutput(int id_student, int num_hours_week, String comentarios, String rama) {
        this.id_student = id_student;
        this.num_hours_week = num_hours_week;
        this.comentarios = comentarios;
        this.rama = rama;
    }

    @Override
    public String toString() {
        return "StudentOutput{" +
                "id_student=" + id_student +
                ", num_hours_week=" + num_hours_week +
                ", comentarios='" + comentarios + '\'' +
                ", rama='" + rama + '\'' +
                ", asignaturas=" + asignaturas +
                '}';
    }

     */
}
