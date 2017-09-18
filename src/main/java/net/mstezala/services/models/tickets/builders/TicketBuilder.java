package net.mstezala.services.models.tickets.builders;

import net.mstezala.services.models.Ticket;

public abstract class TicketBuilder {

    protected Ticket ticket;

    protected abstract void compose();

    public Ticket build() {
        compose();
        return ticket;
    }
}
