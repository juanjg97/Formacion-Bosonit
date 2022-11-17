package org.example;

public class Person {

    //Atributos de la clase
    String name = "";
    String town = "";
    int age = 0;

    //Constructores
    //1.- En caso de que se den todos los valores
    public Person(String name, String town, int age){
        this.name = name;
        this.town=town;
        this.age=age;
    }
    //2.- En caso de que sólo se de la ciudad
    public Person(String name){
        this.name=name;
    }
    //3.- En caso de que se de el nombre y la ciudad
    public Person(String name, String town){
        this.name = name;
        this.town=town;
    }

    //4.- En caso de que se de el nombre y la edad
    public Person(String name, int age){
        this.name = name;
        this.age=age;
    }

    public void mostrarDatos(){
        String mensaje = "";
        if(this.age==0){
            mensaje= "Name: "+ this.name + " Town: " + this.town + " Age: " + "unknow";
        }
        if(this.town.equals("")){
            mensaje= "Name: " + this.name + " Town: " + "unknown" + " Age: " +  this.age;
        }
        if(this.age == 0 && this.town.equals("")){
            mensaje= "Name: " + this.name + " Town: " + "unknown" + " Age: " + "unknown";
        }
        mensaje= "Name: " + this.name + " Town: " + this.town + " Age: " + this.age ;
        System.out.println(mensaje);
    }
}
