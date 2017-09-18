package net.mstezala.services.implementations;

import net.mstezala.services.models.Slots.SlotInfo;
import net.mstezala.Helpers.TicketMachine;
import net.mstezala.configurations.MachineConfiguration;
import net.mstezala.services.MachineService;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;

public class MachineServiceImpl implements MachineService {

    private final TicketMachine ticketMachine;

    private MachineServiceImpl() {
        ticketMachine = new TicketMachine();
    }

    public static final class SingletonHolder {
        private static final MachineServiceImpl MACHINE_SERVICE = new MachineServiceImpl();

        public static MachineServiceImpl getInstance() {
            return MACHINE_SERVICE;
        }
    }

    public Nominal createNominal(BigDecimal value) {
        return new Nominal(value);
    }

    public TicketMachine getTicketMachine() {
        return ticketMachine;
    }

    public void setStandardConfiguration() {
        MachineConfiguration.setStandardMachineConfiguration(ticketMachine);
    }

    @Override
    public String getInfoAboutTicketsInMachine() {
        return "\nBilety w maszynie: " + new SlotInfo(ticketMachine.getTicketSlot()).getInfo();
    }

    @Override
    public String getInfoAboutMachineCoins() {
        return "\nMonety w maszynie: " + new SlotInfo(ticketMachine.getMachineCoinSlot()).getInfo() + "\nŁączna wartość monet w maszynie to: " + ticketMachine.getMachineCoinSlot().sumCoins().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    @Override
    public String getInfoAboutCustomerCoins() {
        return "\nMonety  KLIENTA w maszynie: " + new SlotInfo(ticketMachine.getCustomerCoinSlot()).getInfo() + "Łączna wartość monet KLIENTA to: " + ticketMachine.getCustomerCoinSlot().sumCoins().setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }
}
