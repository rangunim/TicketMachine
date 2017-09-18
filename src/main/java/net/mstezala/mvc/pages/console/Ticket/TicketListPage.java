package net.mstezala.mvc.pages.console.Ticket;

import net.mstezala.mvc.models.Model;
import net.mstezala.mvc.models.ShowTicketModel;
import net.mstezala.services.models.Ticket;
import net.mstezala.mvc.pages.Page;

import java.util.Map;

public class TicketListPage implements Page {

    private Model model;

    public TicketListPage(Model model) {
        this.model = model;
    }

    @Override
    public void show() {
        System.out.println("\nWybierz bilet: ");
        for (Map.Entry<Integer, Ticket> elem : ((ShowTicketModel) model).getTicketMap().entrySet()) {
            System.out.println(elem.getKey() + ") " + elem.getValue().getName());
        }
        System.out.println((((ShowTicketModel) model).getTicketMap().size() + 1) + ") Powrót do poprzedniego menu");
        System.out.println("0) Anuluj zakup");
        System.out.print("Twój wybór: ");
    }
}
