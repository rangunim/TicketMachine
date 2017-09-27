package net.mstezala.services.models;

import java.math.BigDecimal;

//TODO constructor and setter with Scale ( value.setScale(2,BigDecimal.ROUND_HALF_UP);)
public class Nominal implements Comparable<Nominal> {
    /**
     * Represent a nominal value.
     */
    private BigDecimal value;

    /**
     * Construct a new Nominal with a value .
     *
     * @param value The nominal value of costructed nominal.
     */
    public Nominal(BigDecimal value) {
        this.value = value;
    }

    /**
     * Give a reference to nominal value object.
     * @return a reference to nominal value object.
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Set a new  nominal value for this(nominal) object.
     * @param value a new  nominal value which will be set for this(nominal) object.
     */
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
