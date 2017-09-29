package net.mstezala.services.implementations;

import net.mstezala.configurations.MachineConfiguration;
import net.mstezala.services.Slots.SlotInfo;
import net.mstezala.services.Slots.TicketSlot;
import net.mstezala.services.TicketService;
import net.mstezala.services.models.tickets.builders.*;
import net.mstezala.services.models.Ticket;
import net.mstezala.services.models.states.Discount35PercentTicketPriceValue;
import net.mstezala.services.models.states.DiscountTicketPriceValue;

import java.util.Collection;
import java.util.HashSet;

public class TicketServiceImpl implements TicketService {

    /**
     * The machine ticketSlot slot contains tickets which machine actually have.
     * It also contains a limit (maximum number) of each ticket which machine can have.
     */
    private final TicketSlot ticketSlot;

    /**
     * Constructs a new empty Machine Service. It is use only by SingletonHolder inner class.
     */
    private TicketServiceImpl() {
        ticketSlot = new TicketSlot();
    }

    /**
     * SingletonHolder class contains a MachineServiceImpl singleton instance object  and allow get reference to it.
     */
    public static final class SingletonHolder {
        private static final TicketServiceImpl TICKET_SERVICE = new TicketServiceImpl();

        /**
         * Give a reference to TicketServiceImpl instance object
         *
         * @return reference to TicketServiceImpl Singleton instance object
         */
        public static final TicketServiceImpl getInstance() {
            return TICKET_SERVICE;
        }
    }

    /**
     * Creates a new Ticket object based on the builder attributes
     * @param builder that contains the values of the ticket creation parameters like price, name,etc.
     * @return new Ticket object
     */
    public Ticket create(TicketBuilder builder) {
        return builder.build();
    }

    /**
     *  /**
     * Creates a new Ticket object based on the builder attributes and set created ticket a  price discount.
     * @param builder that contains the values of the ticket creation parameters like price, name,etc.
     * @param discount   has discount value. The new price of ticket is calculate in DiscountTicketPriceValue object.
     * @return new Ticket object
     */
    public Ticket create(TicketBuilder builder, DiscountTicketPriceValue discount) {
        Ticket ticket = create(builder);
        setDiscountTicketPrice(ticket, discount);
        return ticket;
    }

    /**
     * Give all ticket that are SINGLE type.
     * @return collection of tickets that are SINGLE type
     */
    @Override
    public Collection<Ticket> getSingleTypeTickets() {
        Collection<Ticket> result = new HashSet<>();
        result.add(create(new NormalTicketBuilder()));
        result.add(create(new SpecialTicketBuilder()));
        return result;
    }

    /**
     * Give all ticket that are TIME type.
     * @return collection of tickets that are TIME type
     */
    @Override
    public Collection<Ticket> getTimeTypeTickets() {
        Collection<Ticket> result = new HashSet<>();
        result.add(create(new Time30MinTicketBuilder()));
        result.add(create(new Time60MinTicketBuilder()));
        result.add(create(new Time90MinTicketBuilder()));
        result.add(create(new Time24hTicketBuilder()));
        return result;
    }

    /**
     * Set the discount for ticket.
     * @param ticket ticket to be set a discount.
     * @param discount  has discount value. The new price of ticket is calculate in DiscountTicketPriceValue object.
     */
    public void setDiscountTicketPrice(Ticket ticket, DiscountTicketPriceValue discount) {
        ticket.setDiscount(discount);
    }

    /**
     * Set the standard discount for ticket. Standard Discount value is 35% a normal ticket price.
     * @param ticket ticket to be set a discount.
     */
    public void setStandardValueDiscountTicketPrice(Ticket ticket) {
        setDiscountTicketPrice(ticket, new Discount35PercentTicketPriceValue());
    }

    /**
     * Give a reference to ticket Slot field
     *
     * @return reference to ticket Slot field
     */
    public TicketSlot getTicketSlot() {
        return ticketSlot;
    }


    /**
     * Give a info about tickets (type, number, etc.) on ticket machine.
     *
     * @return a info about tickets (type, number, etc.) on ticket machine.
     */
    @Override
    public String getInfoAboutTicketsInMachine() {
        return "\nBilety w maszynie: " + new SlotInfo(ticketSlot).getInfo();
    }

    /**
     * Set a typical(standard) configuration for ticket machine. It contains a set parameters as like:
     * number of ticket types in machine,
     * number of nominals in machine,
     * limit a nominals each type in machine,
     * limit a ticket each type in machine.
     *
     * @see{MachineConfiguration} class.
     */
    public void setStandardConfiguration4TicketSlot() {
        MachineConfiguration.setStandardMachineConfiguration4TicketSlot(ticketSlot);
    }
}