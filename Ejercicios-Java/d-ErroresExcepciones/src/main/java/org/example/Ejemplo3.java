package org.example;

/*
* IllegalArgumentException hereda de runtime exception por lo que no necesita ser manejada
* Exception necesita ser manejada, se agrega throws al método y try catch cuando se llama
*   Otra opción es también poner throws en el método main
*
* */


public class Ejemplo3 {

    static double dividir(double  a,double b){
        if(b!=0){
            return (a/b);

        }else{
            throw new IllegalArgumentException("No se puede dividir entre 0");
        }
    }

    static double dividir1(int a,int b) throws Exception {
        if(b!=0){
            return a/b;
        }else{
            throw new Exception ("No se puede dividir entre 0");
        }
    }

    //------------------------------------------------------------------------
    public static void main(String[] args) throws Exception {
        double res = dividir(3.0,0.0);
        System.out.println(res);

        double res1 = dividir1(3,4);
        System.out.println(res1);

    }
}
