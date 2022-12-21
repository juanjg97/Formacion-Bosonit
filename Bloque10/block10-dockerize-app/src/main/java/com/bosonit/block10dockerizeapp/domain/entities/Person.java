package com.bosonit.block10dockerizeapp.domain.entities;

import com.bosonit.block10dockerizeapp.DTOS.PersonInput;
import com.bosonit.block10dockerizeapp.DTOS.PersonOutput;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Indicamos que esta clase será una tabla en una BDD
@Data //Genera los getters y setters
@NoArgsConstructor //Genera un constructor sin parámetros
@AllArgsConstructor //Genera un constructor con todos los parámetros
public class Person {
    @Id //Considera como PK a la variable debajo de esta anotación.
    @GeneratedValue //En caso de no indicar la id, genera automáticamente la llave primaria
    @Column(name = "id") //Mapeamos, buena práctica.
    Integer id;
    @Column(name = "nombre")
    String nombre;
    @Column(name = "edad")
    Integer edad;
    @Column(name = "poblacion")
    String poblacion;

}