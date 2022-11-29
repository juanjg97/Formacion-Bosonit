package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaximoMinimo {

    static int minimo(List<Integer> lista_de_numeros){
        int min = lista_de_numeros.get(0);
        for(Integer numero : lista_de_numeros){
            if (numero<min){
                min=numero;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(7,3,2,5,7,8);
        System.out.println("El número mínimo es: "+minimo(numeros));

        //Usando Streams
        Integer valor_minimo = numeros.stream().min(Comparator.naturalOrder()).get();
        System.out.println("El número mínimo es: "+valor_minimo);
    }
}
