package com.bosonit.block16ticket.repositories;

import com.bosonit.block16ticket.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
