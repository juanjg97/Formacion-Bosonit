package com.bosonit.springdatavalidation.controllers.dtos;

import java.util.List;

public class ProfesorOutput {
    int id_profesor;
    String comentarios;
    String rama;
    List<StudentOutput> students;
}
