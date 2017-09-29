package net.mstezala.services;

import net.mstezala.helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.Slots.CoinSlot;
import net.mstezala.services.Slots.TicketSlot;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;
import java.util.Map;

public interface MachineService {

    /**
     * Create a new Nominal object with value in argument method
     *
     * @param value this value will be value of return new Nominal object
     * @return new Nominal  object with value in argument method
     */
    Nominal createNominal(BigDecimal value);

    /**
     * Add customer nominals (coins) to machine nominal (coin) slot.
     * @throws CrossMaxNumberException It is give a throw when limit of nominal (coin) type in machine slot is full and method try cross this limit.
     */
    void addCustomerCoins2MachineCoinSlot() throws CrossMaxNumberException;

    /**
     * Designate which and how many coins(nominals) should be returned customers as like coins. It's return CoinSlot which contains coins for customer
     * or null if designate change is impossible or there is no change.
     * @param totalPay The value represent of total pay for products
     * @return Map object which contains nominals which and how many should be returned to customer or null if designate change is impossible or there is no change.
     */
    Map<Nominal, Integer> getChange(BigDecimal totalPay);

    /**
     * Sum a change value
     * @param change change in map where key is nominal type and value is a number of nominal type.
     * @return total change value
     */
    BigDecimal sumChangeValue(Map<Nominal, Integer> change);

    /**
     * Give a info about  coins(nominals) (type, number, etc.) on ticket machine.
     * @return a info about coins(nominals) (type, number, etc.) on ticket machine.
     */
    String getInfoAboutMachineCoins();

    /**
     * Give a info about customer coins (nominals) (type, number, etc.) on ticket machine.
     * It only coins, which customer add to machine, but the purchase did not come to an end.
     * In else cases return empty information string.
     * @return a info about customer coins (nominals) (type, number, etc.) on ticket machine.
     */
    String getInfoAboutCustomerCoins();

    //TODO remove getters!
    /**
     * Give a reference to machine Coin Slot field
     * @return reference to machine Coin Slot field
     */
    CoinSlot getMachineCoinSlot();

    /**
     * Give a reference to ticket Slot field
     *
     * @return reference to ticket Slot field
     */
    TicketSlot getTicketSlot();

    /**
     * Give a reference to customerCoinSlot field
     *
     * @return reference to customerCoinSlot field
     */
    CoinSlot getCustomerCoinSlot();

    /**
     * Give a info about tickets (type, number, etc.) on ticket machine.
     *
     * @return a info about tickets (type, number, etc.) on ticket machine.
     */
    String getInfoAboutTicketsInMachine();


    /**
     * Set a typical(standard) configuration for ticket machine. It contains a set parameteres as like:
     * number of ticket types in machine,
     * number of nominals in machine,
     * limit a nominals each type in machine,
     * limit a ticket each type in machine.
     *
     * @see{MachineConfiguration} class.
     */
    void setStandardConfiguration();
}
