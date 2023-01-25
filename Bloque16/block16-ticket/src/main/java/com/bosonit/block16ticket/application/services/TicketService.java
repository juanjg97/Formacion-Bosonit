package com.bosonit.block16ticket.application.services;


import com.bosonit.block16ticket.domain.entities.Ticket;

public interface TicketService {
    Ticket saveTicket(int idPassenger, int idTrip);
    Ticket getTicketById(int idTicket);
}
