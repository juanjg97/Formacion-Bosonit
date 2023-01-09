package org.example.application.services;

import org.example.Domain.Entities.Examen;
import org.example.repositories.ExamenRerpository;
import org.example.repositories.PreguntasRepository;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImp implements ExamenService {

    private ExamenRerpository examenRerpository;
    private PreguntasRepository preguntasRepository;

    public ExamenServiceImp(ExamenRerpository examenRerpository,
                            PreguntasRepository preguntasRepository) {
        this.examenRerpository = examenRerpository;
        this.preguntasRepository=preguntasRepository;
    }

    @Override
    public Examen findExamenPorNombre(String nombre) {

        Optional<Examen> examenOptional = examenRerpository
                .findAll()
                .stream()
                .filter(examen -> examen.getNombre().contains(nombre)).findFirst();

        Examen examen = null;

        if(examenOptional.isPresent()){
            examen = examenOptional.orElseThrow();
        }
        return examen;
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
        Examen examenOptional = findExamenPorNombre(nombre);
        Examen examen = null;

        if(examenOptional!=null){
            examen=examenOptional;
            List<String> preguntas = preguntasRepository.findPreguntasPorExamenId(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }
}
