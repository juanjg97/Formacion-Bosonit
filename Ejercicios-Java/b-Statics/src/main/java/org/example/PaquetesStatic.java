package org.example;
//Importación de un sólo miembro
import static java.lang.Math.sqrt;

//Importación de todos los miembros
//import static java.lang.Math.*;


public class PaquetesStatic {

    public static void main(String[] args) {
        var raiz =0.0;
        var potencia=0.0;

        //Ya no es necesario usar la clase Math.
        raiz=sqrt(25);

        //Es necesario usar la clase Math
        potencia=Math.pow(2,2);
    }

}
