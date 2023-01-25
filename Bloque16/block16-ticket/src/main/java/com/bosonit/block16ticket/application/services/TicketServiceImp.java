package com.bosonit.block16ticket.application.services;

import com.bosonit.block16ticket.domain.entities.Ticket;
import com.bosonit.block16ticket.feignclients.ClienteFeignClient;
import com.bosonit.block16ticket.feignclients.ViajeFeignClient;
import com.bosonit.block16ticket.models.Cliente;
import com.bosonit.block16ticket.models.Viaje;
import com.bosonit.block16ticket.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketServiceImp implements TicketService{
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ClienteFeignClient clienteFeignClient;
    @Autowired
    ViajeFeignClient viajeFeignClient;

    @Override
    public Ticket saveTicket(int idPassenger, int idTrip) {
        Cliente passenger = clienteFeignClient.getClienteFromFeign(idPassenger);
        Viaje viaje = viajeFeignClient.getViajeFromFeign(idTrip);

        Ticket ticket = new Ticket();
        ticket.setIdCliente(passenger.getIdCliente());
        ticket.setNombreCliente(passenger.getNombre());
        ticket.setApellidoCliente(passenger.getApellido());
        ticket.setEmailCliente(passenger.getEmail());
        ticket.setTripOrigin(viaje.getOrigin());
        ticket.setTripDestination(viaje.getDestination());
        ticket.setDepartureDate(viaje.getDepartureDate());
        ticket.setArrivalDate(viaje.getArrivalDate());

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(int idTicket) {
        Ticket ticket = ticketRepository.findById(idTicket).orElseThrow();
        return ticket;
    }


}
