package net.mstezala.mvc.pages.console.Machine;

import net.mstezala.mvc.models.Model;
import net.mstezala.services.models.Nominal;
import net.mstezala.services.models.Ticket;
import net.mstezala.mvc.pages.Page;

import java.math.BigDecimal;
import java.util.Map;

public class PrintTicketPage implements Page {

    private Model model;

    public PrintTicketPage(Model model) {
        this.model = model;
    }

    @Override
    public void show() {
        System.out.println("\nKupiłeś " + model.get("numberTicketChosen") + "x [" + ((Ticket) model.get("chosenTicket")).getName() + "] bilet.");
        System.out.println("Bilet ten był za " + ((Ticket) model.get("chosenTicket")).getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " PLN za sztukę.");

        Map<Nominal, Integer> change = (Map<Nominal, Integer>) model.get("change");
        if (change != null && !change.isEmpty()) {
            System.out.println("Otrzymana reszta to: ");
            for (Map.Entry<Nominal, Integer> entry : change.entrySet()) {
                System.out.println("\t " + entry.getValue() + "x " + entry.getKey().getValue().setScale(2, BigDecimal.ROUND_HALF_UP) + " PLN");
            }
        }
        System.out.println("\n\n");
    }
}
