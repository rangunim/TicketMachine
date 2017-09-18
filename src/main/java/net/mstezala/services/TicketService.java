package net.mstezala.services;

import net.mstezala.services.tickets.builders.TicketBuilder;
import net.mstezala.services.tickets.models.Ticket;
import net.mstezala.services.tickets.models.states.DiscountTicketPriceValue;

/**
 * Created by administrator on 13.09.17.
 */
public interface TicketService {
    Ticket create(TicketBuilder builder);

    Ticket create(TicketBuilder builder, DiscountTicketPriceValue discount);

    void setDiscountTicketPrice(Ticket ticket, DiscountTicketPriceValue discount);
}
