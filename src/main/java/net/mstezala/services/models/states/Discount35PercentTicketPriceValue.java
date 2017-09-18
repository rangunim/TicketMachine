package net.mstezala.services.models.states;

import java.math.BigDecimal;

public class Discount35PercentTicketPriceValue implements DiscountTicketPriceValue {

    public BigDecimal calculate(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0 ? price.multiply(BigDecimal.ONE.subtract(BigDecimal.valueOf(0.35))) : BigDecimal.ZERO;
    }
}
