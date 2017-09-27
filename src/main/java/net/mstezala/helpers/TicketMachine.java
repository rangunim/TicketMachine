package net.mstezala.helpers;

import net.mstezala.services.Slots.CoinSlot;
import net.mstezala.services.Slots.TicketSlot;
import net.mstezala.helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class TicketMachine {
    /**
     * The machine coin slot contains coins (nominals) which machine actually have.
     * It also contains a limit (maximum number) of each nominal which machine can have.
     */
    private final CoinSlot machineCoinSlot;

    /**
     * The machine ticketSlot slot contains tickets which machine actually have.
     * It also contains a limit (maximum number) of each ticket which machine can have.
     */
    private final TicketSlot ticketSlot;

    /**
     * This field contains only coins (nominals) which customers added to machine.
     */
    private CoinSlot customerCoinSlot; //TODO change to  Map<Nominal,Integer> type

    /**
     * Construct a new empty TicketMachine object.
     */
    public TicketMachine() {
        machineCoinSlot = new CoinSlot();
        ticketSlot = new TicketSlot();
        customerCoinSlot = new CoinSlot();
    }

    /**
     * Designate which and how many coins(nominals) should be returned customers as like coins. It's return CoinSlot which contains coins for customer
     * or null if designate change is impossible or there is no change.
     *
     * @param totalPay The value represent of total pay for products
     * @return CoinSlot object which contains nominals which should be returned to customer or null if designate change is impossible or there is no change.
     */
    public CoinSlot getChange(BigDecimal totalPay) { //TODO change returned type to Map<Nominal,Integer>
        List<Nominal> nominals = machineCoinSlot.getNominalTypes().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        BigDecimal changeValue = customerCoinSlot.sumCoins().subtract(totalPay).setScale(2, BigDecimal.ROUND_HALF_UP);
        if (changeValue.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }
        CoinSlot result = null;
        while (result == null && !nominals.isEmpty()) {
            try {
                result = getChange(changeValue, nominals);
            } catch (CrossMaxNumberException e) { //Sneaky Throw
                System.err.println(e.getMessage());
            }
            nominals.remove(0); //TODO THINK ABOUT IT WAY
        }
        return result;
    }

    /**
     * Fills the CoinSlot object with correct nominals that are the change
     *
     * @param changeValue - change value size
     * @param nominals    - contains  nominal types which could be in machine
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
     * @param changeValue - actual value which a piece of change
     * @param nominals -  contains  nominal types
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

    /**
     * Give a reference to machine Coin Slot field
     * @return reference to machine Coin Slot field
     */
    public CoinSlot getMachineCoinSlot() {
        return machineCoinSlot;
    }

    /**
     * Give a reference to ticket Slot field
     * @return reference to ticket Slot field
     */
    public TicketSlot getTicketSlot() {
        return ticketSlot;
    }

    /**
     * Give a reference to customerCoinSlot field
     * @return reference to customerCoinSlot field
     */
    public CoinSlot getCustomerCoinSlot() {
        return customerCoinSlot;
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
