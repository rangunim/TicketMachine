package net.mstezala.services.models.Slots;


import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class CoinSlot extends Slot<Nominal> {

    public Collection<Nominal> getNominalTypes() {
        return getObjectNumber().keySet().stream().sorted().collect(Collectors.toList());
    }

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
