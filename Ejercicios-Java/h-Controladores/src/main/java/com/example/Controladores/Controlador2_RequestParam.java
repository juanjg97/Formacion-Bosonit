package com.example.Controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

//Obtenemos valores sin escribirlos en la URL
@RestController
@RequestMapping("/controlador2")
public class Controlador2_RequestParam {
    //Usar PARAMS en POSTMAN y la key es la variable del método
    @GetMapping(value="/api/hola")
    public String api(@RequestParam (name="key") String value) {
        return value;
    }
    //Parecido a la función de value, pero ahora name para dar el nombre al parámetro
    @GetMapping(value="/api1/hola")
    public String api1(@RequestParam(name="key1") String value1,
                       @RequestParam (          ) String key2) {
        return "El valor es: " + value1 + " y el nombre es: " + key2;
    }

    //---------------------------------------Para que los parámetros no seab obligatorios
    @GetMapping(value="/api2/hola")
    public String api2(@RequestParam(required = false) String key1) {
        return "El valor es: " + key1;
    }

    //Optional
    @GetMapping(value="/api3/hola")
    public String api3(@RequestParam Optional<String> key1) {
        return (key1.isPresent()) ? ("El valor es: " + key1.get()) : ("No se ha digitado valor");
    }

    //Parámetros por defecto
    @GetMapping(value="/api4/hola")
    public String api4(@RequestParam(defaultValue = "Valor por defecto") String key1) {
        return key1;
    }

    //Map en POSTMAN key1,key2,key3...
    @GetMapping(value="/api5/hola")
    public String api5(@RequestParam Map<String,String> keys) {
        return keys.toString();
    }
    //Varlios valores en una sola llave
    @GetMapping(value="/api6/hola")
    public String api6(@RequestParam List<String> keyx) {
        return keyx.toString();
    }
    /*Si tratamos de mandar varios parámetros cuando estamos utilizando una lista no vamos a obtener un error pero sencillamente el resto de parámetros no serán tomados en cuenta:
     */






}
