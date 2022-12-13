package com.bosonit.examen_jpa_cascade;

import com.bosonit.examen_jpa_cascade.application.services.ClienteServiceImp;
import com.bosonit.examen_jpa_cascade.application.services.FacturaServiceImp;
import com.bosonit.examen_jpa_cascade.domain.entities.Cliente;
import com.bosonit.examen_jpa_cascade.domain.entities.Factura;
import com.bosonit.examen_jpa_cascade.domain.entities.Linea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ExamenJpaCascadeApplication implements CommandLineRunner {

	//Inyección de dependencias para usar Métodos CRUD y Lógica de negocio
	@Autowired
	ClienteServiceImp clienteService;
	@Autowired
	FacturaServiceImp facturaService;

	public static void main(String[] args) {
		SpringApplication.run(ExamenJpaCascadeApplication.class, args);
	}

	//Sobreescribimos el método run de la interfaz CommandLineRunner para que al iniciar spring
	//ejecute este código.
	@Override
	public void run(String... args) throws Exception {
		//Creamos y construimos un cliente
		Cliente cliente = new Cliente("Juan");

		//Guardamos en la BDD usando el servicio
		clienteService.addCliente(cliente);

		//Creamos una factura vacia para después, primero obtener su pk y pasarla a las líneas y segundo
		//construirla usando las dos líneas que crearemos y construiremos abajo.
		Factura factura = new Factura();

		//Creamos y construimos dos lineas
		List <String> pructosLista = Arrays.asList("Modelo-Especial","Corona");
		List <Double> preciosLista = Arrays.asList(45.00,100.00);

		//Cuando pasamos "factura" le "pasamos el id de la factura" ya que se relacionan por medio de ésta.
		Linea lineaUno = new Linea(pructosLista.get(0),3,preciosLista.get(0),factura);
		Linea lineaDos = new Linea(pructosLista.get(1),5,preciosLista.get(1),factura);

		//Creamos una lista de lineas y le agregamos las lineas previas
		List<Linea> lineasLista = Arrays.asList(lineaUno,lineaDos);

		//Obtenemos el importe total
		Double importe_total_factura= lineaUno.getPrecio()+ lineaDos.getPrecio();

		//Construimos la factura que creamos previamente, como estaba vacía, la llenamos con el método Add.
		//Guardamos la factura en la BDD.
		facturaService.addFactura(factura,importe_total_factura,lineasLista, cliente.getId_cliente());


		//Al guardar la factura que tiene el OneToMany, se guarda automáticamente también en la bdd las líneas.
		//También si borramos la factura se borran automáticamente las líneas.
	}
}
