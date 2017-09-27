package net.mstezala.services.Slots;


import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class CoinSlot extends Slot<Nominal> {

    /**
     * Give a sorted (by comparable method in Nominal class) nominals collection in slot.
     *
     * @return sorted nominal collection in slot
     */
    public Collection<Nominal> getSortedNominalTypes() {
        return getObjectNumber().keySet().stream().sorted().collect(Collectors.toList());
    }

    /**
     * Give the total nominal value of the coins in the slot. In other words, it returns a cash value.
     * @return a total nominal value of the coins in the slot by formula: SUM(nominal value * number of nominal in slot)
     */
    public BigDecimal sumCoins() {
        BigDecimal result = BigDecimal.ZERO;
        if (getObjectNumber().isEmpty()) {
            return result;
        }
        for (Map.Entry<Nominal, Integer> set : getObjectNumber().entrySet()) {
            result = result.add(set.getKey().getValue().multiply(BigDecimal.valueOf(set.getValue())));
        }
        return result;
    }


}
