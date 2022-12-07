package com.bosonit.block7crud.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *Para la transferencia de información entre capas, usaremos DTOs.
 *Transmitir datos student desde el controlador a la capa de servicio,
 *el cual llamaremos StudentInputDto
 */
@Data //Escribe getters y setters
@AllArgsConstructor //Genera un constructor con todos los argumentos
@NoArgsConstructor // Genera un constructor vacío
public class PersonInputDto {
    Integer id;
    String nombre;
    Integer edad;
    String poblacion;

}
