package net.mstezala.services.models.tickets.builders;

import net.mstezala.services.models.Ticket;
import net.mstezala.services.models.tickets.enums.TicketType;

import java.math.BigDecimal;

public class Time24hTicketBuilder extends TicketBuilder {
    public void compose() {
        ticket = new Ticket();
        ticket.setType(TicketType.TIME);
        ticket.setName("24 h");
        ticket.setPrice(BigDecimal.valueOf(11.00));
    }
}
