package org.example.constants;

import org.example.Domain.Entities.Examen;

import java.util.Arrays;
import java.util.List;

public class DATA {
    public final static List<Examen> EXAMENES_LIST = Arrays.asList(new Examen(5L,"Matemáticas"), new Examen(6L,"Español"), new Examen(7L,"Física"));
    public final static List<String> PREGUNTAS_LIST = Arrays.asList("Aritmetica","Integrales","Geometría");
}
