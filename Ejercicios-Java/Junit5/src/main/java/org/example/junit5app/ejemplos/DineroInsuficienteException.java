package org.example.junit5app.ejemplos;

public class DineroInsuficienteException extends RuntimeException {
    public DineroInsuficienteException(String message) {
        super(message);
    }
}
