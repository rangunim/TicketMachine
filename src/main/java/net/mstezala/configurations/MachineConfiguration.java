package net.mstezala.configurations;

import net.mstezala.services.models.Slots.CoinSlot;
import net.mstezala.Helpers.TicketMachine;
import net.mstezala.Helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.MachineService;
import net.mstezala.services.TicketService;
import net.mstezala.services.models.Nominal;
import net.mstezala.services.models.Ticket;

import java.math.BigDecimal;
import java.util.Collection;

public class MachineConfiguration {

    private static MachineService machineService = ApplicationConfiguration.machineService;
    private static TicketService ticketService = ApplicationConfiguration.ticketService;

    public static void setStandardMachineConfiguration(TicketMachine ticketMachine) {
        try {
            setStandardTicketsList(ticketMachine, 200);
            setStandardCoinsInMachine(ticketMachine);
        } catch (CrossMaxNumberException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void setStandardTicketsList(TicketMachine ticketMachine, int ticketNumber) throws CrossMaxNumberException {
        Collection<Ticket> tickets = ticketService.getSingleTypeTickets();
        tickets.addAll(ticketService.getTimeTypeTickets());

        for (Ticket ticket : tickets) {
            ticketMachine.getTicketSlot().add(ticket, ticketNumber);
            ticketMachine.getTicketSlot().setLimitObjectNumber(ticket, ticketNumber);
        }
    }

    private static void setStandardCoinsInMachine(TicketMachine ticketMachine) throws CrossMaxNumberException {
        CoinSlot machineSlot = ticketMachine.getMachineCoinSlot();
        addNominal2MachineSlotWithLimitNumber(machineSlot, BigDecimal.valueOf(0.10), 90, 200);
        addNominal2MachineSlotWithLimitNumber(machineSlot, BigDecimal.valueOf(0.20), 80, 200);
        addNominal2MachineSlotWithLimitNumber(machineSlot, BigDecimal.valueOf(0.50), 70, 200);
        addNominal2MachineSlotWithLimitNumber(machineSlot, BigDecimal.valueOf(1.00), 60, 200);
        addNominal2MachineSlotWithLimitNumber(machineSlot, BigDecimal.valueOf(2.00), 50, 200);
        addNominal2MachineSlotWithLimitNumber(machineSlot, BigDecimal.valueOf(5.00), 40, 200);
    }

    private static void addNominal2MachineSlotWithLimitNumber(CoinSlot machineSlot, BigDecimal nominalValue, int nominalNumber2Add, int limitNumber) throws CrossMaxNumberException {
        Nominal nominal = machineService.createNominal(nominalValue);
        machineSlot.add(nominal, nominalNumber2Add);
        machineSlot.setLimitObjectNumber(nominal, limitNumber);
    }

}
