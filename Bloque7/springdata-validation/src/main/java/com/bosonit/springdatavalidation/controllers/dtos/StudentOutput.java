package com.bosonit.springdatavalidation.controllers.dtos;

import java.util.List;

public class StudentOutput {
    int id_student;
    int num_hours_week;
    String comentarios;
    String rama;
    List<AsignaturaOutput> asignaturas;
}
