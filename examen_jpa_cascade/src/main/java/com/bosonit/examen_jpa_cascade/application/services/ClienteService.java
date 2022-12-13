package com.bosonit.examen_jpa_cascade.application.services;


import com.bosonit.examen_jpa_cascade.domain.entities.Cliente;

/*
* Métodos que contendrán la lógica de negocio y la lógica CRUD usando los repositorios
* Add utilizará .save() del repositorio, get utilizará .findById() del repositorio
*
* Devuelven un objeto de la misma clase (la mayoría de la veces)
* */
public interface ClienteService {
    //Método para agregar clientes a la BDD
    Cliente addCliente(Cliente cliente);
    //Método para buscar y obtener clientes de la bdd
    Cliente getClienteById(int id);
}
