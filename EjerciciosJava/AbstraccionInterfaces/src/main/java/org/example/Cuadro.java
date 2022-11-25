package org.example;

public class Cuadro extends Figura {
    //Atributos
    private double lado;

    //Constructor
    public Cuadro(String color, double lado) {
        super(color);
        this.lado = lado;
    }
    //Getters y Setters

    public double getLado() {
        return lado;
    }

    public void setLado(double lado) {
        this.lado = lado;
    }

    //Métodos
    //Sobre escribe un método de una clase abstracta
    @Override
    public double calcularArea() {
        return 2 * getLado();
    }
}
