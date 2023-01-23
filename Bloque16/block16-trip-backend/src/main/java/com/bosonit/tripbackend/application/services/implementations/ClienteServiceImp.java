package com.bosonit.tripbackend.application.services.implementations;

import com.bosonit.tripbackend.Mappers.ClienteMapper;
import com.bosonit.tripbackend.application.services.interfaces.ClienteService;
import com.bosonit.tripbackend.domain.entities.Cliente;
import com.bosonit.tripbackend.dtos.ClienteInput;
import com.bosonit.tripbackend.dtos.ClienteOutput;
import com.bosonit.tripbackend.dtos.ClienteOutput;
import com.bosonit.tripbackend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ClienteServiceImp implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ClienteOutput addCliente(ClienteInput clienteInput) throws Exception {
        Cliente Cliente = ClienteMapper.cMapper.ClienteInputToCliente(clienteInput);
        ClienteOutput ClienteOutput= ClienteMapper.cMapper.ClienteToClienteOutput(clienteRepository.save(Cliente));
        System.out.println("Cliente agregada");
        return ClienteOutput;
    }

    @Override
    public ClienteOutput getClienteById(int idCliente) {
        Cliente c = clienteRepository.findById(idCliente).orElseThrow();
        ClienteOutput cO = ClienteMapper.cMapper.ClienteToClienteOutput(c);
        return cO;
    }

    @Override
    public Iterable<ClienteOutput> getAllClientes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Iterable <Cliente> clientes = clienteRepository.findAll(pageRequest).getContent();
        Iterable<ClienteOutput> clientesO = StreamSupport
                .stream(clientes.spliterator(),false)
                .map(Cliente -> ClienteMapper.cMapper.ClienteToClienteOutput(Cliente)).toList();

        return clientesO;
    }

    @Override
    public ClienteOutput updateCliente(int idCliente, ClienteInput clienteInput) {
      Cliente c = clienteRepository.findById(clienteInput.getIdCliente()).orElseThrow();

      c.setIdCliente(clienteInput.getIdCliente());
      c.setApellido(clienteInput.getApellido());
      c.setNombre(clienteInput.getNombre());
      c.setEdad(clienteInput.getEdad());
      c.setEmail(clienteInput.getEmail());
      c.setTeléfono(clienteInput.getTeléfono());

      ClienteOutput cO = ClienteMapper.cMapper.ClienteToClienteOutput(clienteRepository.save(c));

      return cO;
    }

    @Override
    public void deleteClienteById(int idCliente) {
        clienteRepository.findById(idCliente).orElseThrow();
        clienteRepository.deleteById(idCliente);
    }
}
