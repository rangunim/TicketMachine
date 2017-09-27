package net.mstezala.services.implementations;

import net.mstezala.helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.Slots.SlotInfo;
import net.mstezala.helpers.TicketMachine;
import net.mstezala.configurations.MachineConfiguration;
import net.mstezala.services.MachineService;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;
import java.util.Map;

public class MachineServiceImpl implements MachineService {

    private final TicketMachine ticketMachine;

    /**
     * Constructs a new empty Machine Service. It is use only by SingletonHolder inner class.
     */
    private MachineServiceImpl() {
        ticketMachine = new TicketMachine();
    }

    /**
     * SingletonHolder class contains a MachineServiceImpl singleton instance object  and allow get reference to it.
     */
    public static final class SingletonHolder {
        private static final MachineServiceImpl MACHINE_SERVICE = new MachineServiceImpl();

        /**
         * Give a reference to MachineServiceImpl instance object
         *
         * @return reference to MachineServiceImpl Singleton instance object
         */
        public static MachineServiceImpl getInstance() {
            return MACHINE_SERVICE;
        }
    }

    /**
     * Create a new Nominal object with value in argument method
     * @param value this value will be value of return new Nominal object
     * @return new Nominal  object with value in argument method
     */
    public Nominal createNominal(BigDecimal value) {
        return new Nominal(value);
    }

    /**
     * Give a reference to ticketMachine object
     * @return a reference to ticketMachine object
     */
    public TicketMachine getTicketMachine() {
        return ticketMachine;
    }


    /**
     * Set a typical(standard) configuration for ticket machine. It contains a set parameteres as like:
     *  number of ticket types in machine,
     *  number of nominals in machine,
     *  limit a nominals each type in machine,
     *  limit a ticket each type in machine.
     *  @see{MachineConfiguration} class.
     */
    public void setStandardConfiguration() {
        MachineConfiguration.setStandardMachineConfiguration(ticketMachine);
    }

    /**
     * Add customer nominals (coins) to machine nominal (coin) slot.
     * @throws CrossMaxNumberException It is give a throw when limit of nominal (coin) type in machine slot is full and method try cross this limit.
     */
    public void addCustomerCoins2MachineCoinSlot() throws CrossMaxNumberException {
        Map<Nominal, Integer> customerCoins = ticketMachine.getCustomerCoinSlot().getObjectNumber();
        for (Map.Entry<Nominal, Integer> customerCoin : customerCoins.entrySet()) {
            ticketMachine.getMachineCoinSlot().add(customerCoin.getKey(), customerCoin.getValue());
        }
    }

    /**
     * Give a info about tickets (type, number, etc.) on ticket machine.
     * @return a info about tickets (type, number, etc.) on ticket machine.
     */
    @Override
    public String getInfoAboutTicketsInMachine() {
        return "\nBilety w maszynie: " + new SlotInfo(ticketMachine.getTicketSlot()).getInfo();
    }

    /**
     * Give a info about  coins(nominals) (type, number, etc.) on ticket machine.
     * @return a info about coins(nominals) (type, number, etc.) on ticket machine.
     */
    @Override
    public String getInfoAboutMachineCoins() {
        return "\nMonety w maszynie: " + new SlotInfo(ticketMachine.getMachineCoinSlot()).getInfo() + "\nŁączna wartość monet w maszynie to: " + ticketMachine.getMachineCoinSlot().sumCoins().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * Give a info about customer coins (nominals) (type, number, etc.) on ticket machine.
     * It only coins, which customer add to machine, but the purchase did not come to an end.
     * In else cases return empty information string.
     * @return a info about customer coins (nominals) (type, number, etc.) on ticket machine.
     */
    @Override
    public String getInfoAboutCustomerCoins() {
        return "\nMonety  KLIENTA w maszynie: " + new SlotInfo(ticketMachine.getCustomerCoinSlot()).getInfo() + "Łączna wartość monet KLIENTA to: " + ticketMachine.getCustomerCoinSlot().sumCoins().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }




}
