package org.example;

import org.example.Domain.Entities.Examen;
import org.example.application.services.ExamenServiceImp;
import org.example.constants.DATA;
import org.example.repositories.ExamenRerpository;
import org.example.repositories.PreguntasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImpTest {

    //Elementos en común que utiliza cada test
    //(Atributos de la clase ExamenServiceImpTest )

    //Objetos a mockear
    private ExamenRerpository examenRerpository;
    private PreguntasRepository preguntasRepository;

    //Objetos a inyectar los mocks
    private ExamenServiceImp examenService;


    //Este métodos se ejecutan antes de cada test
    @BeforeEach
    void setUp() {
        //El mock sólo se le puede hacer a métodos públicos o default
        //Hacemos un mock de la clase examenRepository que contiene el método findAll
         this.examenRerpository =Mockito.mock(ExamenRerpository.class);
         this.preguntasRepository = Mockito.mock(PreguntasRepository.class);
        //Inyectamos el mock en la clase examenService, que en su constructor tiene un objeto examenRepositoru
         this.examenService = new ExamenServiceImp(examenRerpository,preguntasRepository);
    }


    @Test
    void findExamenPorNombreTest() {
        //Creamos el objeto que queremos simular que devuelve el método .findAll() del repositorio
        List<Examen> examenList = Arrays.asList(new Examen(5L,"Matemáticas"), new Examen(6L,"Español"), new Examen(7L,"Física"));

        //Cuando se ejecute el método .findAll() del repositorio, retorna examenList en lugar de lo que devolvería el método original
        Mockito.when(examenRerpository.findAll()).thenReturn(examenList);

        //Creamos el objeto examen para hacer test, usa el método findExamenPorNombre() que a su vez usa el método findAll()
        Examen examen = examenService.findExamenPorNombre("Matemáticas");

        System.out.println(examen);

        assertNotNull(examen);
        assertEquals(5L,examen.getId());
        assertEquals("Matemáticas",examen.getNombre());
    }

    @Test
    @Disabled
    void findExamenPorNombreTest2() {
        List<Examen> examenList = Collections.emptyList();
        Mockito.when(examenRerpository.findAll()).thenReturn(examenList);
        Examen examen = examenService.findExamenPorNombre("Matemáticas");
        System.out.println(examen);

        assertNotNull(examen);
        assertEquals(5L,examen.getId());
        assertEquals("Matemáticas",examen.getNombre());
    }

    @Test
    void getExamenConPreguntas() {
        //Creamos el objeto que queremos simular que devuelve el método .findAll() del repositorio
        List<Examen> examenList = DATA.EXAMENES_LIST;
        //Cuando se ejecute el método .findAll() del repositorio, retorna examenList en lugar de lo que devolvería el método original
        Mockito.when(examenRerpository.findAll()).thenReturn(examenList);
        Mockito.when(preguntasRepository.findPreguntasPorExamenId(6L)).thenReturn(DATA.PREGUNTAS_LIST);
        /*
        * 5L => Matemáticas
        * 6L => Español
        * 7l => Física
        * */


        //Creamos el objeto examen para hacer test, usa el método findExamenPorNombre() que a su vez usa el método findAll()
        Examen examen = examenService.findExamenPorNombreConPreguntas("Español");
        assertEquals(3,examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Geometría"));
    }

    @Test
    void getExamenConPreguntasVerify() {
        List<Examen> examenList = DATA.EXAMENES_LIST;
        Mockito.when(examenRerpository.findAll()).thenReturn(examenList);
        Mockito.when(preguntasRepository.findPreguntasPorExamenId(6L)).thenReturn(DATA.PREGUNTAS_LIST);
        Examen examen = examenService.findExamenPorNombreConPreguntas("Español");
        assertEquals(3,examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("Geometría"));

        //Verificar que sí se mandan a llavar esos métodos.
        Mockito.verify(preguntasRepository).findPreguntasPorExamenId(Mockito.anyLong());
        Mockito.verify(examenRerpository).findAll();
    }
}