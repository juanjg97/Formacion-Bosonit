package org.example.repositories;

import org.example.Domain.Entities.Examen;

import java.util.List;

public interface ExamenRerpository {
    List<Examen> findAll();
}
