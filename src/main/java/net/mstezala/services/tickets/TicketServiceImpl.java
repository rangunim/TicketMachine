package net.mstezala.services.tickets;

import net.mstezala.services.TicketService;
import net.mstezala.services.tickets.builders.TicketBuilder;
import net.mstezala.services.tickets.models.Ticket;
import net.mstezala.services.tickets.models.states.DiscountTicketPriceValue;

import java.math.BigDecimal;

public class TicketServiceImpl implements TicketService {

    public Ticket create(TicketBuilder builder) {
        return builder.build();
    }

    public Ticket create(TicketBuilder builder, DiscountTicketPriceValue discount) {
        Ticket ticket = create(builder);
        setDiscountTicketPrice(ticket, discount);
        return ticket;
    }

    public void setDiscountTicketPrice(Ticket ticket, DiscountTicketPriceValue discount) {
        ticket.setDiscount(discount);

    }
}