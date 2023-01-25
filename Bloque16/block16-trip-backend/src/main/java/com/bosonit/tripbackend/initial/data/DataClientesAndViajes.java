package com.bosonit.tripbackend.initial.data;

import com.bosonit.tripbackend.domain.entities.Cliente;
import com.bosonit.tripbackend.domain.entities.Viaje;
import com.bosonit.tripbackend.repositories.ClienteRepository;
import com.bosonit.tripbackend.repositories.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataClientesAndViajes {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ViajeRepository viajeRepository;

    @Bean
    public void createData(){
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();
        Cliente cliente4 = new Cliente();
        Cliente cliente5 = new Cliente();

        cliente1.setNombre("Juan");
        cliente1.setApellido("Jasso");
        cliente1.setEdad(25);
        cliente1.setTeléfono("12345");
        cliente1.setEmail("juan@mail.com");

        cliente2.setNombre("Alan");
        cliente2.setApellido("Sandoval");
        cliente2.setEdad(26);
        cliente2.setTeléfono("1111111");
        cliente2.setEmail("alan@mail.com");

        cliente3.setNombre("Diana");
        cliente3.setApellido("Glez");
        cliente3.setEdad(24);
        cliente3.setTeléfono("222222");
        cliente3.setEmail("diana@mail.com");

        cliente4.setNombre("Fabiola");
        cliente4.setApellido("Galván");
        cliente4.setEdad(27);
        cliente4.setTeléfono("4444444");
        cliente4.setEmail("faby@mail.com");

        cliente5.setNombre("Mich");
        cliente5.setApellido("Almendarez");
        cliente5.setEdad(24);
        cliente5.setTeléfono("67777777");
        cliente5.setEmail("mich@mail.com");

        clienteRepository.save(cliente1);
        clienteRepository.save(cliente2);
        clienteRepository.save(cliente3);
        clienteRepository.save(cliente4);
        clienteRepository.save(cliente5);

        Viaje viaje1 = new Viaje();
        Viaje viaje2 = new Viaje();
        Viaje viaje3 = new Viaje();


        viaje1.setOrigin("Toluca");
        viaje1.setDestination("Cdmx");
        viaje1.setStatus(true);
        viaje1.setDepartureDate("1-1-2022");
        viaje1.setArrivalDate("2-1-2022");

        viaje2.setOrigin("Morelia");
        viaje2.setDestination("Qro");
        viaje2.setStatus(true);
        viaje2.setDepartureDate("3-1-2022");
        viaje2.setArrivalDate("4-1-2022");

        viaje3.setOrigin("Guadalajara");
        viaje3.setDestination("Monterrey");
        viaje3.setStatus(true);
        viaje3.setDepartureDate("5-1-2022");
        viaje3.setArrivalDate("6-1-2022");

        viaje1.setPassengers(Arrays.asList(cliente1,cliente2));
        viaje2.setPassengers(Arrays.asList(cliente3,cliente4));
        viaje3.setPassengers(Arrays.asList(cliente5));

        viajeRepository.save(viaje1);
        viajeRepository.save(viaje2);
        viajeRepository.save(viaje3);
    }

}
