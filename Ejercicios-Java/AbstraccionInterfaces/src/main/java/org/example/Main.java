package org.example;

//Se usa polimorfismo al sobreescribir los métodos en las clases hijas

public class Main {


    public static void main(String[] args) {
        //Creación de los objetos
        Circulo circulo_1 = new Circulo("Rojo",5);
        Imagen imagen_1 = new Imagen();

        //Uso de las funciones de la clase Figura, herencia y abstracción
        mostrarDatos(circulo_1);
        mostrarDatos(new Cuadro("Azul",5));

        //Uso de las funciones de la interfaz dibujable
        System.out.println("-----------------------");
        mostrarDibujo(circulo_1);
        mostrarDibujo(imagen_1);
    }

    //Métodos------------------------------------------------------
    public static void mostrarDatos(Figura fig){
        //Sólo podemos hacer uso de los métodos que tiene figura, no los métodos propios de cada clase hija
        System.out.println("------Los datos de la figura son -------");
        System.out.println("La clase de la figura es: "+fig.getClass());
        System.out.println("El color de la figura es: "+ fig.getColor());
        System.out.println("El área de la figura es: "+fig.calcularArea());
    }

    public static void mostrarDibujo(Dibujable dibujo){
        dibujo.dibujar();
    }
}