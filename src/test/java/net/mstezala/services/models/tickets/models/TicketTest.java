package net.mstezala.services.models.tickets.models;

import net.mstezala.services.models.Ticket;
import net.mstezala.services.models.tickets.builders.NormalTicketBuilder;
import net.mstezala.services.models.tickets.builders.TicketBuilder;
import net.mstezala.services.models.states.Discount35PercentTicketPriceValue;
import net.mstezala.services.models.states.DiscountTicketPriceValue;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TicketTest {
    private Ticket ticket;
    private TicketBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new NormalTicketBuilder();
        ticket = builder.build();
    }

    @Test
    public void shouldDiscountedPriceBy35Percent() {
        BigDecimal priceBeforeDiscounted = ticket.getPrice();
        ticket = builder.build();
        DiscountTicketPriceValue discount = new Discount35PercentTicketPriceValue();

        ticket.setDiscount(discount);

        assertEquals(priceBeforeDiscounted.multiply(BigDecimal.valueOf(0.65)), ticket.getPrice());
    }
}