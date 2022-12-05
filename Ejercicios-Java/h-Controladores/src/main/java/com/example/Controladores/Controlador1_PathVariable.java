package com.example.Controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/controlador1")
public class Controlador1_PathVariable {
    //Endpoint simple
    @GetMapping("/saludar")
    public String saludar(){
        return "Hola";
    }
    //Misma respuesta para dos endpoints diferentes
    @GetMapping(value={"/saludar2",
                       "/saludar3"})
    public String saludar2(){
        return "Hola";
    }
    //PathVariable
    @GetMapping("/hola/usuario/{nombre_usuario}")
    public String saludar_usuario(@PathVariable String nombre_usuario){
        return "Hola "+nombre_usuario;
    }
    //PathVariable value
    @GetMapping("/hola/usuario2/{val_1}")
    public String saludar_usuario1(@PathVariable (value="val_1") String nombre_usuario ){
        return "Hola "+nombre_usuario;
    }
    //PathVariable varios values
    @GetMapping("/hola/usuario/nombre_completo/{n}/{a}")
    public String nombre(@PathVariable(value="n") String nombre,
                         @PathVariable (value="a") String apellido){
        return "Hello World " + nombre + " " + apellido;
    }
    //PathVariable Map => Aquí no usamos value=, en el método.
    @GetMapping("/hola/usuario/nombre_completo2/{nombre}/{apellido}")
    public String nombre2(@PathVariable Map<String,String> mapaValores) {
        String nombre =mapaValores.get("nombre");
        String apellido=mapaValores.get("apellido");
        return "Hello World " +nombre + " " + apellido;
    }

    //--------------------------------------Para que los parámetros no sean obligatorios
    @GetMapping(value = { "/api/hola1",
                          "/api/hola1/{x}" })
    public String api(@PathVariable(required = false,value ="x") String id) {
        //La variable resultado recibirá el valor1 en el caso de que la condición
        // sea true o bien el valor2 en el caso de que la condición sea false.
        String resultado = (id==null) ? ("Hello World") : ("Hello World"+id);
        return resultado;
    }

    //Utilizar Optionals  => ya no tenemos que escribir required = false
    //(@PathVariable(value="arg") String id)
    @GetMapping(value= {"/api2/hola2",
                        "/api2/hola2/{id}"})
    public String api2(@PathVariable() Optional<String> id) {
        String resultado = (id.isPresent()) ? ("El valor es: "+id.get()) : ("No existe valor");
        return resultado;
    }

    //Utilizar Map<> => Evitamos escribir required=false
    // => No usamos (@PathVariable(value="arg") String id)
        @GetMapping(value = { "/api3/hola",
                              "/api3/hola/{id}"})
        public String api3(@PathVariable Map<String, String> mapa) {
            String resultado = (mapa==null) ? ("Sin id") : ("El id es: "+mapa.get("id"));
            return resultado;
        }

        //----------------------------- Valores por defecto
        @GetMapping(value= {"/api4/hola",
                            "/api4/hola/{id}"})
        public String api4(@PathVariable Optional<String> id) {
            return (id.isPresent()) ? ("Tenemos valor: " + id.get()) : ("Valor por defecto: 'valorAleatorio'");
        }



}
