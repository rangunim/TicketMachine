package net.mstezala.services.implementations;

import net.mstezala.helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.Slots.CoinSlot;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ChangeDesignator {

    private final CoinSlot machineCoinSlot;

    protected ChangeDesignator(CoinSlot machineCoinSlot) {
        this.machineCoinSlot = machineCoinSlot;
    }

    /**
     * Designate which and how many coins(nominals) should be returned customers as like coins. It's return CoinSlot which contains coins for customer
     * or null if designate change is impossible or there is no change.
     *
     * @param changeValue total change value
     * @return Map object which contains nominals which and how many should be returned to customer or null if designate change is impossible or there is no change.
     */
    public Map<Nominal, Integer> getChange(BigDecimal changeValue) {
        List<Nominal> nominals = machineCoinSlot.getSortedNominalTypes().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        CoinSlot result = null;
        while (result == null && !nominals.isEmpty()) {
            try {
                result = getChange(changeValue, nominals);
            } catch (CrossMaxNumberException e) { //Sneaky Thrown
                System.err.println(e.getMessage());
            }
            nominals.remove(0); //TODO THINK ABOUT IT WAY
        }
        return result != null ? result.getObjectNumber() : null;
    }

    /**
     * Fill the CoinSlot object with correct nominals that are the change
     *
     * @param changeValue change value size
     * @param nominals    contains  nominal types which could be in machine
     * @return -  CoinSlot object which contains nominals that are the change  or null if designate change is impossible
     * @throws CrossMaxNumberException
     */
    private CoinSlot getChange(BigDecimal changeValue, Collection<Nominal> nominals) throws CrossMaxNumberException {  //TODO CHECK LAST DIGIT /DIGITS( LENGTH NUMBER EQULAS minValueAvailableNominal)
        CoinSlot result = new CoinSlot();
        while (changeValue.compareTo(BigDecimal.ZERO) > 0) {
            Nominal nominal = getHighestNominalBelowOrEqualValue(changeValue, nominals);
            if (nominal != null) {
                result.add(nominal);
                changeValue = changeValue.subtract(nominal.getValue());
            } else {
                return null;
            }
        }
        return result;
    }

    /**
     * Give the highest nominal from nominals collection, which is below or equal changeValue.
     *
     * @param changeValue actual value which a piece of change
     * @param nominals    contains  nominal types which is sorted in descending of nominal value
     * @return the highest Nominal from nominal collection and is below or equal changeValue
     */
    private Nominal getHighestNominalBelowOrEqualValue(BigDecimal changeValue, Collection<Nominal> nominals) {
        Iterator<Nominal> it = nominals.iterator();
        while (it.hasNext()) {
            Nominal actualNominal = it.next();
            if (machineCoinSlot.getObjectNumber().get(actualNominal) > 0 && actualNominal.getValue().compareTo(changeValue) <= 0) {
                return actualNominal;
            }
        }
        return null;
    }
}
