package net.mstezala.mvc.pages.console.Home;

import net.mstezala.mvc.models.Model;
import net.mstezala.services.models.Ticket;
import net.mstezala.mvc.pages.Page;

import java.math.BigDecimal;
import java.util.Collection;

public class PriceListPage implements Page {

    private Model model;

    public PriceListPage(Model model) {
        this.model = model;
    }

    @Override
    public void show() {
        System.out.println("\n\t ##Cennik biletów##");
        for (Ticket ticket : (Collection<Ticket>) model.get("tickets")) {
            System.out.println((ticket.getType().name().equalsIgnoreCase("SINGLE") ? "[Jednorazowy] " : "[Czasowy] ") + ticket.getName() + " - " + ticket.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " PLN ");
        }
        System.out.println("\t #########\n");
    }
}
