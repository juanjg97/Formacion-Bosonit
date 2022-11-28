package org.example;

//Las clases anónimas utilizan herencia y son útiles para sobreescribir métodos
//sólo para ciertos objetos

class ClaseAnonima1{
    void saludar(){
        System.out.println("Hola 1");
    }
}
//---------------------------------------------------
public class Main {
    public static void main(String[] args) {

        //Objeto 1 con clase anónima
        ClaseAnonima1 objeto1 = new ClaseAnonima1(){
            @Override
            void saludar(){
                System.out.println("Hello 1");
            }
        };

        //Objeto 2 con clase normal
        ClaseAnonima1 objeto2 = new ClaseAnonima1();

        //-------------------------------------------
        objeto1.saludar();
        objeto2.saludar();

    }
}