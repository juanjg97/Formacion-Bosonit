package com.bosonit.springdata.controller;

import com.bosonit.springdata.aplication.service.StudentServiceImp;
import com.bosonit.springdata.controller.dto.StudentInputDto;
import com.bosonit.springdata.controller.dto.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/*
* Clase que se encarga de desplegar y publicar los servicios REST
* Controlador que manipula el flujo de los servicios rest del mciroservico de animes
*
* Los métodos de un controlador rest, se sugiere que devuelvan un objeto de tipo ResponseEntitu
* este objeto permite generar una respuesta http a la petición que se haga
*
* Dentro de estos métodos de Controller usamos los métodos de la lógica de negocio (Objeto de la clase StudentServiceImp)
*
* Después de regresar el objeto que solicita cada petición, también regresamos la respuesta http
* lo hacemos con return ResponseEntity.ok(ObjetoARegresar);
* */

/*La capa controller, sólo va a entender de DTOs que trabajamos en el StudentServiceImp*/

@RestController //Controller para peticiones web
@RequestMapping("/student") //Nombre del endpoint
public class Controller {
    //Inyectamos dependencias de la clase StudentServiceImp para usar sus métodos
    @Autowired
    StudentServiceImp studentService;

    /*
    *POST: Para agregar información => CREATE
    *Indica que al llegar una petición Post, en este caso al endpoint localhost:8080/student,
    *será llamado el método addStudent. La anotación @RequestBody indica que está esperando un objeto de la clase StudentDto en el cuerpo de la petición.
    *StudentInputDto => Transmitir datos student DEL CONTROLADOR A LA CAPA DE SERVICIO
    *StudentOutputDto => Transmitir datos student DE LA CAPA DE SERVICIO AL CONTROLADOR
    */
    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@RequestBody StudentInputDto student) {
        URI location = URI.create("/student");
        StudentOutputDto estudianteDto = studentService //Usamos los métodos de la clase inyectada
                                         .addStudent(student); //Le mandamos un StudentInputDto y devuelve un StudentOutputDto

        return ResponseEntity.created(location)    //Regresamos respuesta
                             .body(estudianteDto); //Regresamos objeto.
    }

    //GET: Para obtener información => READ
    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDto> getStudentById(@PathVariable int id) {
        try {
            StudentOutputDto estudiante = this.studentService.getStudentById(id); //¿Por qué this?
            return ResponseEntity.ok().body(estudiante);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<StudentOutputDto> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return studentService.getAllStudents(pageNumber, pageSize);
    }

    //PUT: Actualizar información => UPDATE
    @PutMapping
    public ResponseEntity<StudentOutputDto> updateStudent(@RequestBody StudentInputDto student) {
        try {
            studentService.getStudentById(student.getId());
            return  ResponseEntity.ok().body(studentService.addStudent(student));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar información
    @DeleteMapping
    public ResponseEntity<String> deleteStudentById(@RequestParam int id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok().body("student with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
