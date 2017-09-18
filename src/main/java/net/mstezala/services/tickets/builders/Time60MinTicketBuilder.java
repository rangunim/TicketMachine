package net.mstezala.services.tickets.builders;

import net.mstezala.services.tickets.models.Ticket;
import net.mstezala.services.tickets.enums.TicketType;

import java.math.BigDecimal;

public class Time60MinTicketBuilder extends TicketBuilder {
    public void compose() {
        ticket = new Ticket();
        ticket.setType(TicketType.TIME);
        ticket.setName("60 min");
        ticket.setPrice(BigDecimal.valueOf(4.50));
    }
}
