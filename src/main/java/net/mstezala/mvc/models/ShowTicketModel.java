package net.mstezala.mvc.models;

import net.mstezala.services.models.Ticket;

import java.util.Map;

public class ShowTicketModel extends Model {

    private Map<Integer, Ticket> ticketMap;

    public Map<Integer, Ticket> getTicketMap() {
        return ticketMap;
    }

    public void setTicketMap(Map<Integer, Ticket> ticketMap) {
        this.ticketMap = ticketMap;
    }
}
