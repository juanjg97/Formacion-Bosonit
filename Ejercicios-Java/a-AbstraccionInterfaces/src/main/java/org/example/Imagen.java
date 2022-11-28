package org.example;

public class Imagen implements Dibujable{

    //Sobre escribe el método de una interfaz
    @Override
    public void dibujar() {

        System.out.println("Se ha dibujado un objeto de tipo"+getClass());
    }
}
