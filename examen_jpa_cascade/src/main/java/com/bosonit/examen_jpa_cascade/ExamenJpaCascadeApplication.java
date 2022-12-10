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
import java.util.List;

@SpringBootApplication
public class ExamenJpaCascadeApplication implements CommandLineRunner {

	@Autowired
	ClienteServiceImp clienteService;
	@Autowired
	FacturaServiceImp facturaService;

	public static void main(String[] args) {
		SpringApplication.run(ExamenJpaCascadeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		clienteService.addCliente(cliente);

		Factura factura = new Factura();

		Linea lineaUno = new Linea();
		lineaUno.setNombre_producto("modelo-especial");
		lineaUno.setCantidad(3.00);
		lineaUno.setPrecio(45.00);
		lineaUno.setFactura(factura);

		Linea lineaDos = new Linea();
		lineaDos.setNombre_producto("corona");
		lineaDos.setCantidad(5.00);
		lineaDos.setPrecio(100.00);
		lineaDos.setFactura(factura);

		List<Linea> lineasLista = new ArrayList<>();
		lineasLista.add(lineaUno);
		lineasLista.add(lineaDos);

		factura.setImporte_factura(lineaUno.getPrecio()+lineaDos.getPrecio());
		factura.setLineasFactura(lineasLista);
		facturaService.addFactura(factura, cliente.getId_cliente());

	}
}
