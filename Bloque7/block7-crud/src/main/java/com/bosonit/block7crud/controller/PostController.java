package com.bosonit.block7crud.controller;

import com.bosonit.block7crud.aplication.service.PersonServiceImp;
import com.bosonit.block7crud.controller.dto.PersonInputDto;
import com.bosonit.block7crud.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
/*
 * Clase que se encarga de desplegar y publicar los servicios REST
 * Controlador que manipula el flujo de los servicios rest del mciroservico de animes
 *
 * Los métodos de un controlador rest, se sugiere que devuelvan un objeto de tipo ResponseEntitu
 * este objeto permite generar una respuesta http a la petición que se haga
 *
 * Dentro de estos métodos de Controller usamos los métodos de la lógica de negocio (Objeto de la clase PersonServiceImp)
 *
 * Después de regresar el objeto que solicita cada petición, también regresamos la respuesta http
 * lo hacemos con return ResponseEntity.ok(ObjetoARegresar);
 * */

/*La capa controller, sólo va a entender de DTOs que trabajamos en el PersonServiceImp*/

@RestController //Controller para peticiones web
@RequestMapping("/persona") //Nombre del endpoint
public class PostController {
    //Inyectamos dependencias de la clase PersonServiceImp para usar sus métodos
    @Autowired
    PersonServiceImp personService;

    /*
     *POST: Para agregar información => CREATE
     *Indica que al llegar una petición Post, en este caso al endpoint localhost:8080/person,
     *será llamado el método addPerson. La anotación @RequestBody indica que está esperando un objeto de la clase PersonDto en el cuerpo de la petición.
     *PersonInputDto => Transmitir datos Person DEL CONTROLADOR A LA CAPA DE SERVICIO
     *PersontOutputDto => Transmitir datos Person DE LA CAPA DE SERVICIO AL CONTROLADOR
     */
    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        URI location = URI.create("/person");
        PersonOutputDto personaDto = personService      //Usamos los métodos de la clase inyectada
                                    .addPerson(person); //Le mandamos un StudentInputDto y devuelve un StudentOutputDto

        return ResponseEntity.created(location)  //Regresamos respuesta
                             .body(personaDto); //Regresamos objeto.
    }


}

