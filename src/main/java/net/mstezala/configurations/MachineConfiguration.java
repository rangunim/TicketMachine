package net.mstezala.configurations;

import net.mstezala.services.Slots.CoinSlot;
import net.mstezala.helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.MachineService;
import net.mstezala.services.Slots.TicketSlot;
import net.mstezala.services.TicketService;
import net.mstezala.services.models.Nominal;
import net.mstezala.services.models.Ticket;

import java.math.BigDecimal;
import java.util.Collection;

public class MachineConfiguration {

    private static MachineService machineService = ApplicationConfiguration.machineService;
    private static TicketService ticketService = ApplicationConfiguration.ticketService;

    /**
     * Set a standard machine configuration. It contains a nominals (coins) on start in machine a ticket type and number of ticket type on start in machine.
     * It also set a limit number of each nominal type.
     *
     * @param ticketSlot
     * @param machineCoinSlot
     */
    public static void setStandardMachineConfiguration(TicketSlot ticketSlot, CoinSlot machineCoinSlot) {
        try {
            setStandardTicketsList(ticketSlot, 2);
            setStandardCoinsInMachine(machineCoinSlot);
        } catch (CrossMaxNumberException e) { //Sneaky thrown
            System.err.println(e.getMessage());
        }
    }

    private static void setStandardTicketsList(TicketSlot ticketSlot, int ticketNumber) throws CrossMaxNumberException {
        Collection<Ticket> tickets = ticketService.getSingleTypeTickets();
        tickets.addAll(ticketService.getTimeTypeTickets());

        for (Ticket ticket : tickets) {
            ticketSlot.add(ticket, ticketNumber);
            ticketSlot.setLimitObjectNumber(ticket, ticketNumber);
        }
    }

    private static void setStandardCoinsInMachine(CoinSlot machineCoinSlot) throws CrossMaxNumberException {
        addNominal2MachineSlotWithLimitNumber(machineCoinSlot, BigDecimal.valueOf(0.10), 60, 100);
        addNominal2MachineSlotWithLimitNumber(machineCoinSlot, BigDecimal.valueOf(0.20), 50, 100);
        addNominal2MachineSlotWithLimitNumber(machineCoinSlot, BigDecimal.valueOf(0.50), 40, 100);
        addNominal2MachineSlotWithLimitNumber(machineCoinSlot, BigDecimal.valueOf(1.00), 30, 100);
        addNominal2MachineSlotWithLimitNumber(machineCoinSlot, BigDecimal.valueOf(2.00), 20, 100);
        addNominal2MachineSlotWithLimitNumber(machineCoinSlot, BigDecimal.valueOf(5.00), 10, 100);
    }

    private static void addNominal2MachineSlotWithLimitNumber(CoinSlot machineSlot, BigDecimal nominalValue, int nominalNumber2Add, int limitNumber) throws CrossMaxNumberException {
        Nominal nominal = machineService.createNominal(nominalValue);
        machineSlot.add(nominal, nominalNumber2Add);
        machineSlot.setLimitObjectNumber(nominal, limitNumber);
    }

}
