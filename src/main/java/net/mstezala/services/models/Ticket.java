package net.mstezala.services.models;

import net.mstezala.services.models.states.DiscountTicketPriceValue;
import net.mstezala.services.models.tickets.enums.TicketType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Ticket {
    private String name;
    private BigDecimal price;
    private TicketType type;
    private DiscountTicketPriceValue discount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return discount != null ? discount.calculate(price) : price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

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
