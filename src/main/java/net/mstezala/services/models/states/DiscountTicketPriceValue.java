package net.mstezala.services.models.states;

import java.math.BigDecimal;

public interface DiscountTicketPriceValue {
    /**
     * Calculate a new price (with a discount)
     *
     * @param price price before discount
     * @return new price with a discount
     */
    BigDecimal calculate(BigDecimal price);
}