package net.mstezala.services;

import net.mstezala.services.Slots.TicketSlot;
import net.mstezala.services.models.tickets.builders.TicketBuilder;
import net.mstezala.services.models.Ticket;
import net.mstezala.services.models.states.DiscountTicketPriceValue;

import java.util.Collection;


public interface TicketService {
    /**
     * Creates a new Ticket object based on the builder attributes
     *
     * @param builder that contains the values of the ticket creation parameters like price, name,etc.
     * @return new Ticket object
     */
    Ticket create(TicketBuilder builder);

    /**
     *  /**
     * Creates a new Ticket object based on the builder attributes and set created ticket a  price discount.
     * @param builder that contains the values of the ticket creation parameters like price, name,etc.
     * @param discount   has discount value. The new price of ticket is calculate in DiscountTicketPriceValue object.
     * @return new Ticket object
     */
    Ticket create(TicketBuilder builder, DiscountTicketPriceValue discount);

    /**
     * Give all ticket that are SINGLE type.
     * @return collection of tickets that are SINGLE type
     */
    Collection<Ticket> getSingleTypeTickets();

    /**
     * Give all ticket that are TIME type.
     * @return collection of tickets that are TIME type
     */
    Collection<Ticket> getTimeTypeTickets();

    /**
     * Set the discount for ticket.
     * @param ticket ticket to be set a discount.
     * @param discount  has discount value. The new price of ticket is calculate in DiscountTicketPriceValue object.
     */
    void setDiscountTicketPrice(Ticket ticket, DiscountTicketPriceValue discount);

    /**
     * Set the standard discount for ticket. Standard Discount value is 35% a normal ticket price.
     * @param ticket ticket to be set a discount.
     */
    void setStandardValueDiscountTicketPrice(Ticket ticket);

    /**
     * Give a reference to ticket Slot field
     *
     * @return reference to ticket Slot field
     */
    TicketSlot getTicketSlot();

    /**
     * Give a info about tickets (type, number, etc.) on ticket machine.
     *
     * @return a info about tickets (type, number, etc.) on ticket machine.
     */
    String getInfoAboutTicketsInMachine();

    /**
     * Set a typical(standard) configuration for ticket machine. It contains a set parameters as like:
     * number of ticket types in machine,
     * number of nominals in machine,
     * limit a nominals each type in machine,
     * limit a ticket each type in machine.
     *
     * @see{MachineConfiguration} class.
     */
    void setStandardConfiguration4TicketSlot();

}
