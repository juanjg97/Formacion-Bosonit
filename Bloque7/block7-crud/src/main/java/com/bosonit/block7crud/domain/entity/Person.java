package com.bosonit.block7crud.domain.entity;

/*
 * Entidad que va a estar Mapeada en la BDD
 * El mapeo tiene la finalidad de facilitar la conversión,
 * el traspaso de datos de un objeto entity a otra entity.
 */

import com.bosonit.block7crud.controller.dto.PersonInputDto;
import com.bosonit.block7crud.controller.dto.PersonOutputDto;
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
    @Column(name="nombre")
    String nombre;
    @Column(name="edad")
    Integer edad;
    @Column(name="poblacion")
    String poblacion;

    /*
     *Usamos DTOs para enviar y recibir datos,
     *necesitamos un par de métodos para transformar esos DTOs en objetos de clase student, y viceversa
     *
     *
     *Constructor de la clase Person, inyectamos a StudentInputDto
     *Para construir un objeto de la Clase Person usamos los atributos de la clase Person InputDto
     */
    public Person(PersonInputDto personInputDto) {
        this.id = personInputDto.getId();
        this.nombre = personInputDto.getNombre();
        this.edad = personInputDto.getEdad();
        this.poblacion = personInputDto.getPoblacion();
    }

    /*
     *Método que crea y regresa un objeto de la clase PersonOutputDto
     *Lo usamos en PersonServiceImp, así cuando tenemos un Objeto de tipo Person, lo transforma a PersonOutputDto
     
    public PersonOutputDto personToPersonOutputDto(Persona persona) {
        return new PersonOutputDto(this.id=persona.getId(),
                                   this.nombre=persona.getNombre(),
                                   this.edad=persona.getEdad(),
                                   this.poblacion=persona.getPoblacion());
    }
*/
        public PersonOutputDto personToPersonOutputDto() {
        return new PersonOutputDto(this.id,this.nombre,this.edad,this.poblacion);
    }


}
