package net.mstezala.services;

import net.mstezala.Helpers.TicketMachine;
import net.mstezala.Helpers.exceptions.CrossMaxNumberException;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;

public interface MachineService {

    Nominal createNominal(BigDecimal value);

    TicketMachine getTicketMachine();

    void setStandardConfiguration();

    String getInfoAboutTicketsInMachine();

    String getInfoAboutMachineCoins();

    String getInfoAboutCustomerCoins();

    void addCustomerCoins2MachineCoinSlot() throws CrossMaxNumberException;

}
