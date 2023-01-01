package org.bosonit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



class CalculadoraTest {

    Calculadora calculadora = new Calculadora();

    @Test
    @Tag("avanzada")
    @DisplayName("Método suma")
    protected void suma() {

        int resultado = calculadora.suma(32, 94);
        assertEquals(126,resultado);
    }

    @Test
    @Tag("avanzada")
    @DisplayName("Método resta")
    protected void resta() {
        int resultado = calculadora.resta(10, 5);
        assertEquals(5,resultado);
    }

    @Test
    @Disabled
    protected void division() {
        double resultado = calculadora.division(10, 5);
        assertEquals(3,resultado);
    }

    @Test
    protected void multiplicacion() {
        double resultado = calculadora.multiplicacion(10, 5);
        assertEquals(50,resultado);
    }
}