package org.example;

class Persona{
    static int numero_de_personas=0;

    public static int getNumero_de_personas() {
        return numero_de_personas;
    }

    public static void setNumero_de_personas(int numero_de_personas) {
        Persona.numero_de_personas = numero_de_personas;
    }

    Persona(){
        System.out.println("Soy el constructor de la clase persona");
        numero_de_personas++;
    }
}
public class Main {

    //Bloque estático
    static int var1;
    static{
        System.out.println("Hola Mundo");
        var1=0;
    }

    //Variable de instancia
    int x = 0;
    int z = 0;
    //Variable estática
    static int y=0;
//----------------------------------------------------
    public static void main(String[] args) {

        //Uso de variables en el método estático maín usando el bloque estático
        System.out.println("Variable cargada desde el bloque estático con valor de: "+var1);

        //Creando un objeto de la clase Main
        Main variable = new Main();
        //Usando el valor de una variable de instancia usando el objeto variable
        System.out.println("El valor de z es: "+variable.z);

        //Uso de la clase Persona usando variables y métodos estáticos
        Persona.setNumero_de_personas(0);
        System.out.println("El número de persoans es: "+Persona.getNumero_de_personas());

        Persona p1 = new Persona();
        Persona p2 = new Persona();

        System.out.println("El valor de x es: ");
        System.out.println("El valor de y es: "+y);

        System.out.println("El número de persoans es: "+Persona.getNumero_de_personas());
    }

}