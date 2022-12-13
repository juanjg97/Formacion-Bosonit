package com.bosonit.examen_jpa_cascade.application.services;

import com.bosonit.examen_jpa_cascade.domain.entities.Cliente;
import com.bosonit.examen_jpa_cascade.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Implementación del servicio, sobreescribimos los métodos del servicio, después serán usados en el controlador o en donde se requieran.
//Se implementa la lógica de negocio, junto con la CRUD con ayuda del repositorio
@Service
public class ClienteServiceImp implements ClienteService{

    //Inyectamos el repositorio para la lógica CRUD
    //.save(), findById(),findAll(),deleteById(),
    @Autowired
    ClienteRepository clienteRepository;

    //Método para agregar clientes a la BDD
    @Override
    public Cliente addCliente(Cliente cliente) {

        return clienteRepository.save(cliente); //Uso del repositorio
    }

    //Método para buscar y obtener clientes de la bdd
    @Override
    public Cliente getClienteById(int id_cliente) {
        //Uso del repositorio, puede devolver optional, por lo que usamos .orElseThrow
        return clienteRepository.findById(id_cliente).orElseThrow();
    }
}
