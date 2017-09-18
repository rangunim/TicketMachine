package net.mstezala.mvc.pages.console.Ticket;

import net.mstezala.mvc.pages.Page;

public class NumberTicketQueryPage implements Page {

    @Override
    public void show() {
        System.out.print("\nWprowadź ilość biletów (1-10): ");
    }
}
