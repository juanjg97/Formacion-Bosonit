package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Archivo Datos = new Archivo();

        try{
            String ruta = "bloque-1-jdk/block1-process-file-and-streams/src/test/fichero.csv";
            List<String> textoDelArchivo = Datos.leer_fichero(ruta);
            System.out.println("Los datos originales en el archivo son: "+textoDelArchivo);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}