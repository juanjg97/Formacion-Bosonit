package com.bosonit.springdata.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Para la transferencia de información entre capas, usaremos DTOs.
Transmitir datos student de la capa de servicio al controlador
el cual llamaremos StudentInputDto,*/
@Data //Escribe getters y setters
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDto {
    int id;
    String name;
    String lastname;
}
