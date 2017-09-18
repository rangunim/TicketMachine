package net.mstezala.services.models;

import java.math.BigDecimal;

public class Nominal implements Comparable<Nominal> {

    private BigDecimal value;

    public Nominal(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nominal coin = (Nominal) o;

        return value.equals(coin.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Nominal o) {
        return (this.value.subtract(o.getValue())).multiply(BigDecimal.valueOf(100)).intValue();
    }

    @Override
    public String toString() {
        return value.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " ";
    }
}
