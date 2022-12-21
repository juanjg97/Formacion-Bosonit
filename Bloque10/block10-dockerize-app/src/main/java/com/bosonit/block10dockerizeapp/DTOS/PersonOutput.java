package com.bosonit.block10dockerizeapp.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Escribe getters y setters
@AllArgsConstructor //Genera un constructor con todos los argumentos
@NoArgsConstructor // Genera un constructor vacío
public class PersonOutput {
    Integer id;
    String nombre;
    Integer edad;
    String poblacion;

}
