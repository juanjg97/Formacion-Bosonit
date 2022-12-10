package com.bosonit.examen_jpa_cascade.application.services;


import com.bosonit.examen_jpa_cascade.domain.entities.Cliente;

public interface ClienteService {
    Cliente addCliente(Cliente cliente);
    Cliente getClienteById(int id);
}
