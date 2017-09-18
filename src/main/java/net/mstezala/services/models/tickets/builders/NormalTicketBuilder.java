package net.mstezala.services.models.tickets.builders;

import net.mstezala.services.models.Ticket;
import net.mstezala.services.models.tickets.enums.TicketType;

import java.math.BigDecimal;

public class NormalTicketBuilder extends TicketBuilder {

    public void compose() {
        ticket = new Ticket();
        ticket.setType(TicketType.SINGLE);
        ticket.setName("Normalny");
        ticket.setPrice(BigDecimal.valueOf(3.00));
    }

}
