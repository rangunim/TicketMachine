package net.mstezala.Helpers;

import net.mstezala.services.models.Slots.CoinSlot;
import net.mstezala.services.models.Slots.TicketSlot;
import net.mstezala.Helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class TicketMachine {
    private final CoinSlot machineCoinSlot;
    private final TicketSlot ticketSlot;

    private CoinSlot customerCoinSlot;

    public TicketMachine() {
        machineCoinSlot = new CoinSlot();
        ticketSlot = new TicketSlot();
        customerCoinSlot = new CoinSlot();
    }

    public CoinSlot getChange(BigDecimal leftPay) {
        List<Nominal> nominals = machineCoinSlot.getNominalTypes().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        BigDecimal changeValue = customerCoinSlot.sumCoins().subtract(leftPay).setScale(2, BigDecimal.ROUND_HALF_UP);
        CoinSlot result = null;
        while (result == null && !nominals.isEmpty()) {
            try {
                result = getChange(changeValue, nominals);
            } catch (CrossMaxNumberException e) {
                System.err.println(e.getMessage());
            }
            nominals.remove(0); //TODO THINK ABOUT IT WAY
        }
        return result;
    }

    private CoinSlot getChange(BigDecimal changeValue, Collection<Nominal> nominals) throws CrossMaxNumberException {
        CoinSlot result = new CoinSlot();
        if (changeValue.compareTo(BigDecimal.ZERO) <= 0) {//TODO EDIT THIS! CHECK LAST DIGIT /DIGITS( LENGTH NUMBER EQULAS minValueAvailableNominal
            return null;
        }

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

    public CoinSlot getMachineCoinSlot() {
        return machineCoinSlot;
    }

    public TicketSlot getTicketSlot() {
        return ticketSlot;
    }

    public CoinSlot getCustomerCoinSlot() {
        return customerCoinSlot;
    }

    public void setCustomerCoinSlot(CoinSlot customerCoinSlot) {
        this.customerCoinSlot = customerCoinSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketMachine that = (TicketMachine) o;

        if (!machineCoinSlot.equals(that.machineCoinSlot)) return false;
        if (!ticketSlot.equals(that.ticketSlot)) return false;
        return customerCoinSlot != null ? customerCoinSlot.equals(that.customerCoinSlot) : that.customerCoinSlot == null;
    }

    @Override
    public int hashCode() {
        int result = machineCoinSlot.hashCode();
        result = 31 * result + ticketSlot.hashCode();
        result = 31 * result + (customerCoinSlot != null ? customerCoinSlot.hashCode() : 0);
        return result;
    }
}
