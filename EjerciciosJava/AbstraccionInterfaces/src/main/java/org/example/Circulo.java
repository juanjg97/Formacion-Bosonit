package org.example;

public class Circulo extends Figura implements Dibujable{
    //Atributos
    private double radio;
    //Constructor
    public Circulo(String color, double radio) {
        super(color);
        this.radio=radio;
    }
    //Getters y Setters
    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
    //Métodos
    //Sobre escribe un método de una clase abstracta
    @Override
    public double calcularArea() {
        return Math.PI*Math.pow(getRadio(),2);
    }
    //Sobreescribe un método de una interfaz
    @Override
    public void dibujar() {
        System.out.println("Se ha dibujado el "+getClass()+"de color "+getRadio());
    }
}