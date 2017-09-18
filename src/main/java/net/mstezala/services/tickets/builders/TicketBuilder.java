package net.mstezala.services.tickets.builders;

import net.mstezala.services.tickets.models.Ticket;

public abstract class TicketBuilder {

    protected Ticket ticket;

    protected abstract void compose();

    public Ticket build() {
        compose();
        return ticket;
    }
}
