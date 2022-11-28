package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            int x =0;
            int y = x/0;
            //Al fallar aquí el programa, se salta esta parte y va al catch
            System.out.println("Después del error");

        //En exception puede ir un tipo en específico
        }catch (Exception excepcion){
            excepcion.printStackTrace();
            System.out.println(excepcion.getMessage());
        }finally {
            System.out.println("Siempre se ejecuta");
        }
    }
}