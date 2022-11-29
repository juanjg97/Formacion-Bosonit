package org.example;
/*
* Podemos pasar como parámetro una función, a una función
* En el primer ejemplo crearemos una función y le pasaremos un lambda como parámetro
* En el segundo ejemplo usaremos la función reduce y le pasaremos un lambda como parámetro
* */

import java.util.Arrays;

//Creamos una interfaz con un método que después pasaremos como argumento.
interface Operaciones{
    //Argumentos para la función lambda
    int OperacionSumar(int num1,int num2);
}

public class Main {
    /*
    * Creamos nuestro propio método para sumar los números de un arrelo
    * Le pasamos el arreglo de números
    * Le pasamos la variable operacion que es del tipo Operaciones (Interfaz)
    * */
    static int sumaTotal(int[] numeros, Operaciones operacion){
        int acumulador = 0;

        for(int numero:numeros){
            //La lambda OperacionSumar, suma el número con el acumulador, para cada número, realizando la suma.
            acumulador=operacion.OperacionSumar(numero,acumulador);
        }
        return acumulador;
    }

    public static void main(String[] args) {
        int [] lista_numeros = {1,2,3,4,5};
        int resultado = 0;
        int resultado2 = 0;
        /*
        * Implementamos la función lambda
        * (parametros) -> expresion
        * Suma el acumulador con el número actual
        * */
        resultado = sumaTotal(lista_numeros,(num_actual,acumulador) -> num_actual+acumulador);
        System.out.println("Resultado creando nuestra propia función: "+resultado);


        //Usando stream y reduce
        //Como observamos usamos una lambda como parámetro en la función reduce.

        resultado2= Arrays.stream(lista_numeros).reduce(0,(a,b)->a+b);
        System.out.println("Resultado usando stream y reduce: "+resultado2);
    }
}