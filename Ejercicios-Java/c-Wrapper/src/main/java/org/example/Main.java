package org.example;

public class Main {
    public static void main(String[] args) {

        //Notar que podemos acceder a los métodos que tengan los Wrapper

        //Mostrar el tipo de datos
        Integer num1 = 8;
        System.out.println("num1 es de tipo " +num1.getClass().getSimpleName());

        //Conversión de Integer a otros tipos de datos
        double num1d = num1.doubleValue();
        System.out.println("num1d es de tipo " + ((Object)num1d).getClass().getSimpleName());
        float num1f = num1.floatValue();
        System.out.println("num1f es de tipo " + ((Object)num1f).getClass().getSimpleName());

        //Covertir string a tipo primitivo
        String valor_s = "12345";
        int valor_i = Integer.parseInt(valor_s);


        System.out.println("valor_s es de tipo " +valor_s.getClass().getSimpleName());
        System.out.println("valor_i es de tipo " + ((Object)valor_i).getClass().getSimpleName());

        //Recibe un string y devuelve el tipo de wrapper indicado
        Integer valor_int = Integer.valueOf("45");
        Float valor_float = Float.valueOf("45");

         //Autoboxing y auto-unboxing
        Integer i=10;
        int z = i;
    }
}