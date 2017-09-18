package net.mstezala.mvc.pages.console.Machine;

import net.mstezala.mvc.models.Model;
import net.mstezala.services.models.Ticket;
import net.mstezala.mvc.pages.Page;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class BuyPage implements Page {

    private Model model;

    public BuyPage(Model model) {
        this.model = model;
    }

    @Override
    public void show() {
        showInfo();
        showMenu();
    }

    private void showInfo() {
        System.out.println("\nWybrano: " + model.get("numberTicketChosen") + "x " + ((Ticket) model.get("chosenTicket")).getName());
        System.out.println("Jeden bilet kosztuje: " + ((Ticket) model.get("chosenTicket")).getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " PLN.");

        BigDecimal totalPay = ((Ticket) model.get("chosenTicket")).getPrice().multiply(BigDecimal.valueOf((int) model.get("numberTicketChosen")));
        System.out.println("Łącznie do zapłaty: " + totalPay.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " PLN");

        BigDecimal addedCoins = (BigDecimal) model.get("addedCoins");
        BigDecimal left2Pay = (addedCoins != null ? totalPay.subtract(addedCoins) : totalPay).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Pozostało do zapłaty: " + left2Pay.toString() + " PLN");
    }

    private void showMenu() {
        System.out.println("\nCo chcesz zrobić?");
        Set<Map.Entry<Integer, BigDecimal>> nominalValueTypes = ((Map<Integer, BigDecimal>) model.get("nominalTypesMap")).entrySet();
        for (Map.Entry<Integer, BigDecimal> nominalValueType : nominalValueTypes) {
            System.out.println(nominalValueType.getKey() + ") Wrzuć " + nominalValueType.getValue().setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " PLN");
        }

        System.out.println("0) Anuluj zakup");
        System.out.print("Twoj wybór: ");
    }


}
