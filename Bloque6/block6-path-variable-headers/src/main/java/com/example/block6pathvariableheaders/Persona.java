package com.example.block6pathvariableheaders;

/*Creamos una clase persona para mandar el objeto solicitado a través de Postman
* esta clase será instanceada al ejecutarse el método getPersona en el ComponenteImp*/
public class Persona {
    //Atributos
    private int num_id;
    private  String nombre;
    private String ciudad;
    //Constructor
    public Persona(int num_id, String nombre, String ciudad) {
        this.num_id = num_id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        System.out.println("Se ha creado una persona");
    }
    //Getters y Setters
    public int getNum_id() {
        return num_id;
    }
    public void setNum_id(int num_id) {
        this.num_id = num_id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
