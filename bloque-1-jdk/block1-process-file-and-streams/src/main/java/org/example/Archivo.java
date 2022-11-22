package org.example;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertEquals;

public class Archivo {

    // Lee un archivo una línea a la vez y devuelve una lista con los datos
    @Test
    public List<String> leer_fichero(String ruta) throws IOException {

        Path path = Paths.get(ruta);

        List<String> datos = Files.readAllLines(path);
        List<Person> personas = new ArrayList<>();

        int contador = 0;

        for (String linea : datos) {
            try {
                contador++;
                personas.add(validar_formato(linea, contador));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Filtrado de personas
        personas.forEach(p -> System.out.println(p));
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        personas.stream()
                .filter(p -> p.getEdad().orElse(0) < 25).forEach(p -> System.out.println(p));

        personas.stream()
                .map(p ->
                {
                    return p.getNombre();
                }).forEach(p -> System.out.println(p));

        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        var persA = personas.stream().filter(p -> !p.getNombre().startsWith("A")).
                collect(Collectors.toList());

        persA.forEach(p -> System.out.println(p));
        return datos;
    }

    // Valida que el formato de una línea sea correcto
    public Person validar_formato(String linea, int nLinea) throws InvalidLineFormatException {
        String[] lineaDividida = linea.split(":");
        long contador = lineaDividida.length;

        Integer edad = null;
        if (contador > 2)
            edad = Integer.parseInt(lineaDividida[2]);
        return new Person(lineaDividida[0],
                edad, lineaDividida[1]);
    }
}
