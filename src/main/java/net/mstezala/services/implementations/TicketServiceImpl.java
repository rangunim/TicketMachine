package net.mstezala.services.implementations;

import net.mstezala.services.TicketService;
import net.mstezala.services.models.tickets.builders.*;
import net.mstezala.services.models.Ticket;
import net.mstezala.services.models.states.Discount35PercentTicketPriceValue;
import net.mstezala.services.models.states.DiscountTicketPriceValue;

import java.util.Collection;
import java.util.HashSet;

public class TicketServiceImpl implements TicketService {
    private TicketServiceImpl() {
    }

    public static final class SingletonHolder {
        private static final TicketServiceImpl TICKET_SERVICE = new TicketServiceImpl();

        public static final TicketServiceImpl getInstance() {
            return TICKET_SERVICE;
        }
    }


    public Ticket create(TicketBuilder builder) {
        return builder.build();
    }

    public Ticket create(TicketBuilder builder, DiscountTicketPriceValue discount) {
        Ticket ticket = create(builder);
        setDiscountTicketPrice(ticket, discount);
        return ticket;
    }

    @Override
    public Collection<Ticket> getSingleTypeTickets() {
        Collection<Ticket> result = new HashSet<>();
        result.add(create(new NormalTicketBuilder()));
        result.add(create(new SpecialTicketBuilder()));
        return result;
    }

    @Override
    public Collection<Ticket> getTimeTypeTickets() {
        Collection<Ticket> result = new HashSet<>();
        result.add(create(new Time30MinTicketBuilder()));
        result.add(create(new Time60MinTicketBuilder()));
        result.add(create(new Time90MinTicketBuilder()));
        result.add(create(new Time24hTicketBuilder()));
        return result;
    }

    public void setDiscountTicketPrice(Ticket ticket, DiscountTicketPriceValue discount) {
        ticket.setDiscount(discount);
    }

    public void setStandardValueDiscountTicketPrice(Ticket ticket) {
        setDiscountTicketPrice(ticket, new Discount35PercentTicketPriceValue());
    }
}