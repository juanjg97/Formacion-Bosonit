package com.bosonit.block16ticket.controllers;

import com.bosonit.block16ticket.application.services.TicketService;
import com.bosonit.block16ticket.domain.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/generateTicket/{idPassenger}/{idTrip}")
    public Ticket generateTicket(@PathVariable("idPassenger") int idPassenger, @PathVariable("idTrip") int idTrip) {
        return ticketService.saveTicket(idPassenger, idTrip);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getTicket/{idTicket}")
    public Ticket generateTicket(@PathVariable("idTicket") int idTicket) {
        Ticket ticket = ticketService.getTicketById(idTicket);
        return ticket;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/hello/{idTicket}")
    public String hello(@PathVariable("idTicket") int idTicket) {
        return "Hello"+idTicket;
    }

}
