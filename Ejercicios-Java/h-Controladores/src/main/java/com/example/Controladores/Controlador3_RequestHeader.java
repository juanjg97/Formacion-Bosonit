package com.example.Controladores;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/*Como ya sabemos, en una petición HTTP podemos mandar headers con cierta información,
como por ejemplo para autenticación*/

@RestController
@RequestMapping("/controlador3")
public class Controlador3_RequestHeader {
    //Usar HEADERS en POSTMAN y la key es la variable del método

    @GetMapping(value="/api/hola")
    public String api(@RequestHeader("key1") String valor1) {
        return valor1;
    }

    //Headers en conjunto => Ingresamos varias llaves y varios valores
    //Map
    @GetMapping(value = "/api1/hola")
    public String api1(@RequestHeader Map<String, String> keys) {
        return keys.toString();
    }

    //Map<String, List<String>> => Mas conveniente
    //Otra manera de acerlo
    @GetMapping(value = "/api2/hola")
    public String api2(@RequestHeader MultiValueMap<String, String> keys) {
        return keys.toString();
    }

    //Atributos, es lo mismo que usar name o que sólo pasar el nombre de la llave
    @GetMapping(value = "/api3/hola")
    public String api3(@RequestHeader(value="key1-accept-language") String valor) {
        return valor;
    }
    //---------------Valores Opcionales
    @GetMapping(value = "/api4/hola")
    public String api4(@RequestHeader(value="key1-accept-language", required = false) String valor) {
        return valor;
    }

    //Valor por defecto (si la clave no es la misma que accept-language, entonces regresa el valor por defecto
    @GetMapping(value = "/api5/hola")
    public String hola(@RequestHeader(value="accept-language", defaultValue="en-US") String valor) {
        return valor;
    }





}
