package org.example.application.services;

import org.example.Domain.Entities.Examen;

public interface ExamenService {
    Examen findExamenPorNombre (String nombre);
    Examen findExamenPorNombreConPreguntas (String nombre);
}
