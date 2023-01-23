package com.bosonit.tripbackend.application.services.implementations;

import com.bosonit.tripbackend.Mappers.ViajeMapper;
import com.bosonit.tripbackend.application.services.interfaces.ViajeService;
import com.bosonit.tripbackend.domain.entities.Cliente;
import com.bosonit.tripbackend.domain.entities.Viaje;
import com.bosonit.tripbackend.dtos.ViajeOutput;
import com.bosonit.tripbackend.dtos.ViajeInput;
import com.bosonit.tripbackend.dtos.ViajeOutput;
import com.bosonit.tripbackend.repositories.ClienteRepository;
import com.bosonit.tripbackend.repositories.ViajeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class ViajeServiceImp implements ViajeService {
    @Autowired
    ViajeRepository viajeRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ViajeOutput addViaje(ViajeInput viajeInput) throws Exception {
        Viaje Viaje = ViajeMapper.vMapper.ViajeInputToViaje(viajeInput);
        ViajeOutput ViajeOutput= ViajeMapper.vMapper.ViajeToViajeOutput(viajeRepository.save(Viaje));
        System.out.println("Viaje agregada");
        return ViajeOutput;
    }

    @Override
    public ViajeOutput getViajeById(int idViaje) {
            Viaje p = viajeRepository.findById(idViaje).orElseThrow();
            ViajeOutput pO = ViajeMapper.vMapper.ViajeToViajeOutput(p);
            return pO;
    }

    @Override
    public Iterable<ViajeOutput> getAllViajes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Iterable <Viaje> viajes = viajeRepository.findAll(pageRequest).getContent();
        Iterable<ViajeOutput> viajesO = StreamSupport
                .stream(viajes.spliterator(),false)
                .map(viaje -> ViajeMapper.vMapper.ViajeToViajeOutput(viaje)).toList();

        return viajesO;
    }

    @Override
    public ViajeOutput updateViaje(int idViaje, ViajeInput viajeInput) {
        Viaje v = viajeRepository.findById(viajeInput.getIdViaje()).orElseThrow();
        v.setIdViaje(viajeInput.getIdViaje());
        v.setOrigin(viajeInput.getOrigin());
        v.setStatus(viajeInput.getStatus());
        //v.setPassenger(viajeInput.getPassenger());
        v.setDestination(viajeInput.getDestination());
        v.setArrivalDate(viajeInput.getArrivalDate());
        v.setDepartureDate(viajeInput.getDepartureDate());

        ViajeOutput vO = ViajeMapper.vMapper.ViajeToViajeOutput(viajeRepository.save(v));

        return vO;
    }

    @Override
    public void deleteViajeById(int idViaje) {
            viajeRepository.findById(idViaje).orElseThrow();
            viajeRepository.deleteById(idViaje);
    }

    @Override
    public ViajeOutput addPassenger(int idViaje, int idPassenger) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow();
        List<Cliente> passengers = viaje.getPassengers();
        int numberOfPassengers = passengers.size();

        if(numberOfPassengers>=3){
            log.error("El viaje alcanzó el número máximo de pasajeros");
            return null;
        }else{
            Cliente cliente = clienteRepository.findById(idPassenger).orElseThrow();
            passengers.add(cliente);
            return ViajeMapper.vMapper.ViajeToViajeOutput(viajeRepository.save(viaje));
        }
    }

    @Override
    public int countPassengers(int idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow();
        return viaje.getPassengers().size();
    }

    @Override
    public void updateStatus(int idViaje, boolean status) {
        Viaje viaje = viajeRepository.findById(idViaje).orElseThrow();
        viaje.setStatus(status);
        viajeRepository.save(viaje);
    }
}
