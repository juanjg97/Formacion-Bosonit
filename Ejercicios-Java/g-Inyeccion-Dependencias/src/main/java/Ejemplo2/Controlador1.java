package Ejemplo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*Las dependencias que inyectemos siempre serán las mismas si tienen los mismos nombres*/

@RestController
@Qualifier("ClaseControlador1")
public class Controlador1 {
    //Inyección de dependencias
    @Autowired
        //@Qualifier("BeanComponente1")
        //@Qualifier("ClaseComponente1")
    Componente1 componente_a;
    @Autowired
    Componente2 componente_b;
    @Autowired
    Componente3 componente_c;
    @Autowired
    Componente4 componente_d;


    @Autowired
   // @Qualifier("ClaseComponente1")
    Componente1 componente_aa;


    //Constructor de la clase
    public Controlador1() { }

    @GetMapping("/c1")
    public String saludarc1(){ return componente_a.getNombre() ;
    }
    @GetMapping("/c2")
    public String saludarc2(){
        return componente_b.sadoDoble();
    }

    @GetMapping("/c3")
    public String saludarc3(){
        return componente_c.saludar();
    }

    @GetMapping("/c4")
    public String saludarc4() {return componente_d.despedirse();}

    public void cn(){
        System.out.println(componente_aa.getNombre());
        //Aquí usamos uso del objeto que inyectamos en la clase Componente2
        componente_b.cambiarNombre();
        //Aquí usamos el objeto que inyectamos en esta clase (Componente1)
        System.out.println(componente_aa.getNombre());
    }
    @GetMapping("cn")
    public void mcn() {
        cn();
    }

}
