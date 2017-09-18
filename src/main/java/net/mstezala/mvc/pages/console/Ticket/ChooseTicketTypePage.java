package net.mstezala.mvc.pages.console.Ticket;

import net.mstezala.mvc.pages.Page;

public class ChooseTicketTypePage implements Page {
    @Override
    public void show() {
        System.out.println("\nWybierz rodzaj biletu: ");
        System.out.println("1) Jednorazowy");
        System.out.println("2) Czasowy");
        System.out.println("3) Powrót do poprzedniego menu");
        System.out.println("0) Anuluj zakup");
        System.out.print("Twój wybór: ");
    }
}
