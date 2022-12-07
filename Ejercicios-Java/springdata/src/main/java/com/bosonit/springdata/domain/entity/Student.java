package com.bosonit.springdata.domain.entity;

import com.bosonit.springdata.controller.dto.StudentInputDto;
import com.bosonit.springdata.controller.dto.StudentOutputDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Entidad que va a estar Mapeada en la BDD
* El mapeo tiene la finalidad de facilitar la conversión,
* el traspaso de datos de un objeto entity a otra entity.
*/

@Entity //Indicamos que esta clase será una tabla en una BDD
@Data //Genera los getters y setters
@NoArgsConstructor //Genera un constructor sin parámetros
@AllArgsConstructor //Genera un constructor con todos los parámetros
public class Student {
    @Id //Considera como PK a la variable debajo de esta anotación.
    @GeneratedValue //En caso de no indicar la id, genera automáticamente la llave primaria
    @Column(name = "id") //Mapeamos, buena práctica.
    int id;
    @Column(name="name")
    String name;
    @Column(name="lastname")
    String lastname;

    /*
    *Usamos DTOs para enviar y recibir datos,
    *necesitamos un par de métodos para transformar esos DTOs en objetos de clase student, y viceversa
    *
    *Constructor de la clase Student, inyectamos a StudentInputDto
    *Para construir un objeto de la Clase Student usamos los atributos de la clase Student InputDto
    */
    public Student(StudentInputDto studentInputDto) {
        System.out.println("Constructor de la clase Student");
        this.id = studentInputDto.getId();
        this.name = studentInputDto.getName();
        this.lastname = studentInputDto.getLastname();
    }

    /*
    *Método que crea y regresa un objeto de la clase StudentOutputDto
    *Lo usamos en StudentServiceImp, así cuando tenemos un Objeto de tipo Student, lo transforma a StudentOutputDto
    */
    public StudentOutputDto studentToStudentOutputDto() {
        return new StudentOutputDto(
                this.id,
                this.name,
                this.lastname
        );
    }

}
