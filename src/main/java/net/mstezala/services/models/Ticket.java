package net.mstezala.services.models;

import net.mstezala.services.models.states.DiscountTicketPriceValue;
import net.mstezala.services.models.tickets.enums.TicketType;

import java.math.BigDecimal;

public class Ticket {
    /**
     * The name of ticket.
     */
    private String name;
    /**
     * The price of ticket.
     */
    private BigDecimal price;
    /**
     * The type of ticket. To get an available ticket types see {@link TicketType}.
     */
    private TicketType type;

    /**
     * Actual discount value for ticket. If this field is null  then ticket have not any discount.
     */
    private DiscountTicketPriceValue discount;

    /**
     * Give a name of ticket.
     *
     * @return a name of ticket.
     */
    public String getName() {
        return name;
    }

    /**
     * Set a  name for this(ticket) object.
     * @param name a  name which will be set for this(ticket) object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Give a price of ticket including possible discount.
     * @return a reference to ticket price if ticket not any discount or new BigDecimal object when ticket has dicount.
     */
    public BigDecimal getPrice() {
        return discount != null ? discount.calculate(price) : price;
    }

    /** Set a price of ticket for this(Ticket) object.
     *
     * @param price a price which will be set for this(Ticket) object.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Give a type of ticket.
     * @return a reference to type of ticket.
     */
    public TicketType getType() {
        return type;
    }

    /**
     * Set a ticket type for this(Ticket) object.
     * @param type  a ticket type which will be set for this(Ticket) object. To get an available ticket types see {@link TicketType}.
     */
    public void setType(TicketType type) {
        this.type = type;
    }

    /**
     * Set a discount for ticket price.
     * @param discount a discount which will be set for this(Ticket) object.
     */
    public void setDiscount(DiscountTicketPriceValue discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (!name.equals(ticket.name)) return false;
        return type == ticket.type;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", price=" + this.getPrice() +
                ", ticketType=" + type +
                '}';
    }
}
