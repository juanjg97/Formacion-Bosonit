package org.example;

//Clase hija de figura
public class Cuadro extends Figura {
    //Atributos
    private double lado;

    //Constructor => Debe contener también los atributos del padre.
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
