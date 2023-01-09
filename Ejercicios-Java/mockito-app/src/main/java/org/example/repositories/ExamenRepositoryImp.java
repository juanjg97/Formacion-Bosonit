package org.example.repositories;

import org.example.Domain.Entities.Examen;

import java.util.Arrays;
import java.util.List;

public class ExamenRepositoryImp implements ExamenRerpository {
    @Override
    public List<Examen> findAll() {
        return Arrays.asList(new Examen(5L,"Mate"),
                new Examen(6L,"Esp"),
                new Examen(7L,"Física"));
    }
}
