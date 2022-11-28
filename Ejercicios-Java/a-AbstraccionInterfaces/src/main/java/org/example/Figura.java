package org.example;

//Clase padre abstracta, no se pueden crear objetos de esta clase, sin embargo las clases hijas que hereden
//tendrán sus atributos y deberán sobreescribir sus métodos.

//Si tenemos un método abstracto la clase también debe ser abstracta
public abstract class Figura {
    //Atributos------------------------------------------------------
    public String color;

    //Constructores---------------------------------------------------
    public Figura(String color) {
        this.color = color;
    }

    //Getters y Setters-----------------------------------------------
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    //Métodos----------------------------------------------------------
    public abstract double calcularArea();

    public void saludar(){
        System.out.println("Hola");
    }
}
