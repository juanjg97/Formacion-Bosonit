package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WildCardGenericos {

    //Sabiendo que el tipo de los argumentos son double
    static double sumarValores(List<Double> lista_valores){
      double suma = 0.0;
      for (Double numero : lista_valores){
          suma=suma+numero;
      }
      return suma;
    }

    //Sin saber de qué tipo son los argumentos, pero los convertiremos a double
    static double sumarValores2(List<? extends Number> lista_valores){
        double suma = 0.0;
        for (Number numero : lista_valores){
            suma=suma+(numero.doubleValue());
        }
        return suma;
    }

    public static void main(String[] args) {
        double res1,res2,res3;

        ArrayList<Double> valores = new ArrayList<>();
        valores.add(1.0);valores.add(2.0);valores.add(3.0);
        res1=sumarValores(valores);
        System.out.println(res1);

        List<Double> valores2 = Arrays.asList(4.0,5.0,6.0);
        res2=sumarValores(valores2);
        System.out.println(res2);

        //Usando wildcard
        res3 = sumarValores2(Arrays.asList(1,10.45F,11.3D,4L));
        System.out.println(res3);

    }
}
