package org.example;

class  EpropiaException extends Exception{

    //Constructor de la clase
    public EpropiaException(String error){
        super(error);
    }
}

public class Epersonalizada {
    static void compararNombre(String titulo) throws EpropiaException {
        if(!titulo.toUpperCase().equals(titulo)){
            throw new EpropiaException("El título debe estár en mayúsculas");
        }
        System.out.println("Titulo correcto");
    }

//Formas de manejar el método en MAIN---------------------------------------------

    /*
    public static void main(String[] args) {
        try{
            compararNombre("JUAN");
        }catch(Exception e1){
            e1.printStackTrace();
        }
    }
*/
    //Otra maneja de manejar el método main
public static void main(String[] args) throws EpropiaException {
       compararNombre("JUaN");
    }
}
