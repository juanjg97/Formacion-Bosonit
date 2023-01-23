package com.bosonit.tripbackend.application.services.interfaces;


import com.bosonit.tripbackend.dtos.ClienteInput;
import com.bosonit.tripbackend.dtos.ClienteOutput;

import java.util.List;

public interface ClienteService {
    ClienteOutput addCliente(ClienteInput clienteInput) throws Exception;
    ClienteOutput getClienteById(int idCliente);
    Iterable<ClienteOutput> getAllClientes(int pageNumber, int pageSize);
    ClienteOutput updateCliente(int idCliente, ClienteInput clienteInput);
    void deleteClienteById(int idCliente);
}
