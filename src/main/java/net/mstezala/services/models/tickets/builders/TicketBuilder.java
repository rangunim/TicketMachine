package net.mstezala.services.models.tickets.builders;

import net.mstezala.services.models.Ticket;

public abstract class TicketBuilder {

    /**
     * A ticket that a builder wants to build.
     */
    protected Ticket ticket;

    /**
     * Compose a ticket object.
     */
    protected abstract void compose();

    /**
     * Consists a ticket object.
     *
     * @return a ticket that before composed by builder.
     */
    public Ticket build() {
        compose();
        return ticket;
    }
}
