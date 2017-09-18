package net.mstezala.mvc.pages.console.Shared;

import net.mstezala.mvc.models.Model;
import net.mstezala.mvc.pages.Page;
import net.mstezala.services.models.Nominal;

import java.math.BigDecimal;
import java.util.Map;

public class CancelBuyPage implements Page {

    private Model model;

    public CancelBuyPage() {
        model = new Model();
    }

    public CancelBuyPage(Model model) {
        this.model = model;
    }

    @Override
    public void show() {
        System.out.println("Transakcja została anulowana.");

        Map<Nominal, Integer> customerCoins = (Map<Nominal, Integer>) model.get("customerCoins");
        if (customerCoins != null && !customerCoins.isEmpty()) {
            System.out.println("Zwracam następujące monety: ");
            for (Map.Entry<Nominal, Integer> customerCoin : customerCoins.entrySet()) {
                System.out.println("\t " + customerCoin.getValue() + "x " + customerCoin.getKey().getValue().setScale(2, BigDecimal.ROUND_HALF_UP) + " PLN");
            }
        }
    }
}
