package com.example.Controladores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador4")
public class Controlador4_RequestBody {

    @PostMapping(value = "/frase" )
    public String agregarPersona (@RequestBody String frase) {
        return frase;
    }
    /*
*       @PostMapping("/addCiudad")
        public void addCiudad(@RequestBody Ciudad ciudad){
        System.out.println("Ciudad agregada");
        servicio.addCiudad(ciudad);}

        *
        @PostMapping(value = "/useradd" )
        public Persona agregarPersona (@RequestBody Persona persona) {
        persona.setEdad(persona.getEdad()+ 1);
        return persona;}
        *
        @PostMapping("/user")
        public Persona getPersona(@RequestBody Persona info){
        //Método addPersona del componente 1 usando la clase Persona
        return componente1.getPersona(info);}

        @PutMapping("/post")
        public HashMap <String,String> getVar(@RequestParam(name= "v1") String var1,
                                              @RequestParam(name= "v2") String var2){
        //Método getClaveValor del componente 1 usando la clase Persona
        return componente1.getClaveValor(var1, var2);
    }
    * */

}
