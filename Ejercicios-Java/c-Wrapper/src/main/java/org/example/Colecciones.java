package org.example;

import java.util.ArrayList;

public class Colecciones {
    public static void main(String[] args) {
        //Almacenar diferentes tipos de datos
        ArrayList lista1 = new ArrayList();

        lista1.add(1);lista1.add("Un string");lista1.add(new Object());
        System.out.println(lista1);

        //Delimitar el tipo de datos.
        ArrayList<String> lista2 = new ArrayList<>();
        lista2.add("String1");lista2.add("String2");
        System.out.println(lista2);
        //Agregar en una posición específica
        lista2.add(1,"String3");
        System.out.println(lista2);

    }
}
