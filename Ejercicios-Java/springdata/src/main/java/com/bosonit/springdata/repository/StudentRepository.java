package com.bosonit.springdata.repository;

import com.bosonit.springdata.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


/*
* En el repository se encuentran las interfaces Java que contendrán el CRUD que hagan las transacciones en las BDD
*
* JpaRepository<Clase para Crud, Tipo PrimaryKey>: Proporciona los métodos para realizar un CRUD a la BDD
*
*El primer parámetro,indica el tipo de objeto que va a mapear a la base de datos, el segundo, la clase que se usa como primary key.
*
*
*Service y repository, se van a comunicar con objetos de clase Student.
*la capa de repository sólo entenderá de objetos de la clase Student.
*
*Spring Data JPA mediante esta interfaz, nos provee la mayoría de los métodos que vamos a necesitar para persistir y leer datos.
*Spring Boot al arrancar examinará nuestro código y si encuentra un interface que extienda de Repository creará la clase con los métodos necesarios.
* Es decir escribirá el código por nosotros. Además de ello, meterá esa clase en el contexto de Spring (como si la anotáramos con un @Component)

 */
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
