package org.example;

//Se pueden crear objetos de clases abstractas usando clases anónimas abstractas
// a diferencia de las clases anónimas simples, aquí es obligatorio sobreescribir todos los métodos

abstract class AbstractaAnonima{
    abstract void saludar();

}

public class AnonimaAbstracta {
    public static void main(String[] args) {
        AbstractaAnonima objeto1 = new AbstractaAnonima() {
            @Override
            void saludar() {
                System.out.println("Hello World");
            }
        };

        objeto1.saludar();
    }
}
