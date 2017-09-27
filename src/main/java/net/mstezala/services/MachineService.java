package net.mstezala.services;

import net.mstezala.helpers.TicketMachine;
import net.mstezala.helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;

public interface MachineService {

    /**
     * Create a new Nominal object with value in argument method
     *
     * @param value this value will be value of return new Nominal object
     * @return new Nominal  object with value in argument method
     */
    Nominal createNominal(BigDecimal value);

    /**
     * Give a reference to ticketMachine object
     * @return a reference to ticketMachine object
     */
    TicketMachine getTicketMachine();

    /**
     * Set a typical(standard) configuration for ticket machine. It contains a set parameteres as like:
     *  number of ticket types in machine,
     *  number of nominals in machine,
     *  limit a nominals each type in machine,
     *  limit a ticket each type in machine.
     *  @see{MachineConfiguration} class.
     */
    void setStandardConfiguration();

    /**
     * Give a info about tickets (type, number, etc.) on ticket machine.
     * @return a info about tickets (type, number, etc.) on ticket machine.
     */
    String getInfoAboutTicketsInMachine();

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

    /**
     * Add customer nominals (coins) to machine nominal (coin) slot.
     * @throws CrossMaxNumberException It is give a throw when limit of nominal (coin) type in machine slot is full and method try cross this limit.
     */
    void addCustomerCoins2MachineCoinSlot() throws CrossMaxNumberException;

}
