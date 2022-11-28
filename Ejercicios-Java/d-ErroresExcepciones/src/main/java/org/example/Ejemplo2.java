package org.example;
//Multiples Catch, las excepciones más genéricas se van poniendo más abajo

public class Ejemplo2 {
    public static void main(String[] args) {
        try {
            int x =10; //0
            int y = 10/x;
            System.out.println("Valor de la división "+y);

            String nombre = "Juan"; //null
            System.out.println(nombre.toString());

            int arreglo [] = {1,2,3};
            System.out.println(arreglo[3]);

        }catch (ArithmeticException e1){
            System.err.printf("Mensaje 1: Aritmethic exception %s \n",e1.getMessage());
        }catch(NullPointerException e2){
            System.err.printf("Mensaje 2: NullPointer exception %s \n",e2.getMessage());
        }catch(Exception e3){
            System.err.printf("Mensaje 3: Exepción de tipo: %s \n",e3.getMessage());
        }finally {
            System.out.println("Siempre se ejecuta");
        }
    }
}
