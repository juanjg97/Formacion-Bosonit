package com.bosonit.block16ticket.initial.data;

import com.bosonit.block16ticket.application.services.TicketService;
import com.bosonit.block16ticket.domain.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateTickets {
    @Autowired
    TicketService ticketService;

    @Bean
    public void createData(){
        ticketService.saveTicket(1,1);
        ticketService.saveTicket(2,1);
        ticketService.saveTicket(3,2);
        ticketService.saveTicket(4,2);
        ticketService.saveTicket(5,3);
    }

}
