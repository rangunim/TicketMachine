package net.mstezala.services.tickets.models.states;

import net.mstezala.services.tickets.models.Ticket;

import java.math.BigDecimal;

public interface DiscountTicketPriceValue {
    BigDecimal calculate(BigDecimal price);
}
