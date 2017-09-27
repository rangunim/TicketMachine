package net.mstezala.services.models.states;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class Discount35PercentTicketPriceValueTest {
    private DiscountTicketPriceValue discount;

    @Before
    public void setUp() throws Exception {
        discount = new Discount35PercentTicketPriceValue();
    }

    @Test
    public void shouldDiscountedPriceBy35Percent() {
        BigDecimal price = BigDecimal.TEN;

        BigDecimal result = discount.calculate(price);

        assertEquals(price.multiply(BigDecimal.valueOf(0.65)), result);
    }

    @Test
    public void shouldCalculateReturnZERO() {
        BigDecimal result = discount.calculate(null);

        assertEquals(BigDecimal.ZERO, result);

        result = discount.calculate(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, result);
    }

}