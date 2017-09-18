package net.mstezala.services.models.states;

import java.math.BigDecimal;

public interface DiscountTicketPriceValue {
    BigDecimal calculate(BigDecimal price);
}
