package net.mstezala.services;

import net.mstezala.services.models.tickets.builders.TicketBuilder;
import net.mstezala.services.models.Ticket;
import net.mstezala.services.models.states.DiscountTicketPriceValue;

import java.util.Collection;


public interface TicketService {
    Ticket create(TicketBuilder builder);

    Ticket create(TicketBuilder builder, DiscountTicketPriceValue discount);

    Collection<Ticket> getSingleTypeTickets();

    Collection<Ticket> getTimeTypeTickets();

    void setDiscountTicketPrice(Ticket ticket, DiscountTicketPriceValue discount);

    void setStandardValueDiscountTicketPrice(Ticket ticket);
}
