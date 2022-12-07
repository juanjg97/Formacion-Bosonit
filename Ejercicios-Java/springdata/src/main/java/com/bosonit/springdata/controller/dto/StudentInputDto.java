package com.bosonit.springdata.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
*Para la transferencia de información entre capas, usaremos DTOs.
*Transmitir datos student desde el controlador a la capa de servicio,
*el cual llamaremos StudentInputDto
*/
@Data //Escribe getters y setters
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {
    int id;
    String name;
    String lastname;
}
