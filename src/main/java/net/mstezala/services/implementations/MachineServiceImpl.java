package net.mstezala.services.implementations;

import net.mstezala.helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.Slots.CoinSlot;
import net.mstezala.services.Slots.SlotInfo;
import net.mstezala.configurations.MachineConfiguration;
import net.mstezala.services.MachineService;
import net.mstezala.services.Slots.TicketSlot;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;
import java.util.*;

public class MachineServiceImpl implements MachineService {

    /**
     * SingletonHolder class contains a MachineServiceImpl singleton instance object  and allow get reference to it.
     */
    public static final class SingletonHolder {
        private static final MachineServiceImpl MACHINE_SERVICE = new MachineServiceImpl();

        /**
         * Give a reference to MachineServiceImpl instance object
         * @return reference to MachineServiceImpl Singleton instance object
         */
        public static MachineServiceImpl getInstance() {
            return MACHINE_SERVICE;
        }
    }


    /**
     * The machine coin slot contains coins (nominals) which machine actually have.
     * It also contains a limit (maximum number) of each nominal which machine can have.
     */
    private final CoinSlot machineCoinSlot;

    /**
     * This field contains only coins (nominals) which customers added to machine.
     */
    private CoinSlot customerCoinSlot;

    /**
     * Constructs a new empty Machine Service Impl. It is use only by SingletonHolder inner class.
     */
    private MachineServiceImpl() {
        machineCoinSlot = new CoinSlot();
        customerCoinSlot = new CoinSlot();
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
     * Add customer nominals (coins) to machine nominal (coin) slot.
     * @throws CrossMaxNumberException It is give a throw when limit of nominal (coin) type in machine slot is full and method try cross this limit.
     */
    public void addCustomerCoins2MachineCoinSlot() throws CrossMaxNumberException {
        Map<Nominal, Integer> customerCoins = customerCoinSlot.getObjectNumber();
        for (Map.Entry<Nominal, Integer> customerCoin : customerCoins.entrySet()) {
            machineCoinSlot.add(customerCoin.getKey(), customerCoin.getValue());
        }
    }

    /**
     * Designate which and how many coins(nominals) should be returned customers as like coins. It's return CoinSlot which contains coins for customer
     * or null if designate change is impossible or there is no change.
     * @param totalPay The value represent of total pay for products
     * @return Map object which contains nominals which and how many should be returned to customer or null if designate change is impossible or there is no change.
     */
    public Map<Nominal, Integer> getChange(BigDecimal totalPay) {
        BigDecimal changeValue = customerCoinSlot.sumCoins().subtract(totalPay).setScale(2, BigDecimal.ROUND_HALF_UP);
        if (changeValue.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }
        return new ChangeDesignator(machineCoinSlot).getChange(changeValue);
    }

    /**
     * Sum a change value
     *
     * @param change change in map where key is nominal type and value is a number of nominal type.
     * @return total change value
     */
    public BigDecimal sumChangeValue(Map<Nominal, Integer> change) {
        CoinSlot changeSlot = new CoinSlot();
        changeSlot.setObjectNumber(change);
        return changeSlot.sumCoins();
    }

    /**
     * Give a info about coins(nominals) (type, number, etc.) on ticket machine.
     * @return a info about coins(nominals) (type, number, etc.) on ticket machine.
     */
    @Override
    public String getInfoAboutMachineCoins() {
        return "\nMonety w maszynie: " + new SlotInfo(machineCoinSlot).getInfo() + "\nŁączna wartość monet w maszynie to: " + machineCoinSlot.sumCoins().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * Give a info about customer coins (nominals) (type, number, etc.) on ticket machine.
     * It only coins, which customer add to machine, but the purchase did not come to an end.
     * In else cases return empty information string.
     * @return a info about customer coins (nominals) (type, number, etc.) on ticket machine.
     */
    @Override
    public String getInfoAboutCustomerCoins() {
        return "\nMonety  KLIENTA w maszynie: " + new SlotInfo(customerCoinSlot).getInfo() + "Łączna wartość monet KLIENTA to: " + customerCoinSlot.sumCoins().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }


    /**
     * Give a reference to machine Coin Slot field
     *
     * @return reference to machine Coin Slot field
     */
    public CoinSlot getMachineCoinSlot() {
        return machineCoinSlot;
    }

    /**
     * Give a reference to customerCoinSlot field
     *
     * @return reference to customerCoinSlot field
     */
    public CoinSlot getCustomerCoinSlot() {
        return customerCoinSlot;
    }




    /**
     * Set a typical(standard) configuration for ticket machine. It contains a set parameters as like:
     * number of ticket types in machine,
     * number of nominals in machine,
     * limit a nominals each type in machine,
     * limit a ticket each type in machine.
     *
     * @see{MachineConfiguration} class.
     */
    public void setStandardConfiguration4MachineCoinSlot() {
        MachineConfiguration.setStandardMachineConfiguration4MachineCoinSlot(machineCoinSlot);
    }

}
