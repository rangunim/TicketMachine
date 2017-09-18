package net.mstezala.mvc.pages.console.Home;

import net.mstezala.mvc.models.MachineStateModel;
import net.mstezala.mvc.pages.Page;

public class TicketMachineStatePage implements Page {
    private MachineStateModel model;

    public TicketMachineStatePage(MachineStateModel model) {
        this.model = model;
    }

    @Override
    public void show() {
        System.out.println(model.getInfoAboutTickets());
        System.out.println(model.getInfoAboutMachineCoins());
        System.out.println(model.getInfoAboutCustomerCoins());
    }
}
