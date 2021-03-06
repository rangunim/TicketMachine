package net.mstezala.mvc.controllers;

import net.mstezala.helpers.exceptions.CrossMaxNumberException;
import net.mstezala.configurations.ApplicationConfiguration;
import net.mstezala.mvc.models.MachineStateModel;
import net.mstezala.mvc.models.Model;
import net.mstezala.mvc.pages.console.Home.TicketMachineStatePage;
import net.mstezala.mvc.pages.console.Machine.BuyPage;
import net.mstezala.mvc.pages.console.Shared.CancelBuyPage;
import net.mstezala.mvc.pages.console.Machine.PrintTicketPage;
import net.mstezala.services.MachineService;
import net.mstezala.services.Slots.CoinSlot;
import net.mstezala.services.models.Nominal;
import net.mstezala.services.models.Ticket;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class MachineController extends AbstractController {

    private MachineService machineService;

    private MachineController() {
        machineService = ApplicationConfiguration.machineService;
    }


    public void getInfo() {
        MachineStateModel model = new MachineStateModel();
        model.setInfoAboutTickets(machineService.getInfoAboutTicketsInMachine());
        model.setInfoAboutMachineCoins(machineService.getInfoAboutMachineCoins());
        model.setInfoAboutCustomerCoins(machineService.getInfoAboutCustomerCoins());
        showPage(new TicketMachineStatePage(model));
    }


    public void buyTicket(Model model) {
        Collection<Nominal> nominals = machineService.getTicketMachine().getMachineCoinSlot().getSortedNominalTypes();
        model.add("nominalTypesMap", convertCollect2Map(nominals.stream().map(x -> x.getValue()).sorted().collect(Collectors.toList())));
        showPage(new BuyPage(model));
        buyTicketPOST(model, getCustomerAction());
    }

    private void buyTicketPOST(Model model, int userAction) {
        if (userAction == 0) {
            cancelBuy(model);
            return;
        }
        BigDecimal addedCoins = machineService.getTicketMachine().getCustomerCoinSlot().sumCoins();
        int numberTicketChosen = (int) model.get("numberTicketChosen");
        Ticket chosenTicket = (Ticket) model.get("chosenTicket");

        BigDecimal totalPay = chosenTicket.getPrice().multiply(BigDecimal.valueOf(numberTicketChosen));
        if (addedCoins.compareTo(totalPay) < 0) {
            Map<Integer, BigDecimal> nominalTypesMap = (Map<Integer, BigDecimal>) model.get("nominalTypesMap");
            BigDecimal coin = nominalTypesMap.get(userAction);
            while (coin == null || !isAddedCoinBelowLSlotLimit(coin)) {
                if (coin != null) {
                    System.out.println("Slot z nominałami " + coin.setScale(0, BigDecimal.ROUND_HALF_UP).toString() + " PLN pełny.");
                }
                userAction = onWrongChoice(nominalTypesMap.size());
                if (userAction == 0) {
                    cancelBuy(model);
                    return;
                }
                coin = nominalTypesMap.get(userAction);
            }

            try {
                machineService.getTicketMachine().getCustomerCoinSlot().add(machineService.createNominal(coin));
                addedCoins = machineService.getTicketMachine().getCustomerCoinSlot().sumCoins();
                model.add("addedCoins", addedCoins);
            } catch (CrossMaxNumberException e) {
                System.err.println(e.getMessage());
            }
            if (addedCoins.compareTo(totalPay) < 0) {
                buyTicket(model);
            } else {
                buyTicketPOST(model, userAction);
            }
        } else {
            CoinSlot change = machineService.getTicketMachine().getChange(totalPay);
            if (addedCoins.compareTo(totalPay) > 0) {
                if (change != null) {
                    addedCoins = addedCoins.subtract(change.sumCoins());
                } else {
                    System.out.println("Nie mogę wydać reszty!.");
                    cancelBuy(model);
                    return;
                }
                //TODO check isPossibleChange() or question
            }

            if (addedCoins.compareTo(totalPay) == 0) {
                if (change != null && change.getObjectNumber() != null && !change.getObjectNumber().isEmpty()) {
                    for (Map.Entry<Nominal, Integer> entry : change.getObjectNumber().entrySet()) {
                        machineService.getTicketMachine().getMachineCoinSlot().remove(entry.getKey(), entry.getValue());
                    }
                    model.add("change", change.getObjectNumber());
                }
                try {
                    machineService.addCustomerCoins2MachineCoinSlot();
                } catch (CrossMaxNumberException e) {
                    System.err.println(e.getMessage());
                }
                machineService.getTicketMachine().getTicketSlot().remove(chosenTicket, numberTicketChosen);
                showPage(new PrintTicketPage(model));
            }

            clearBuy(model);
            back2HomePage();
        }
    }

    private void clearBuy(Model model) {
        model.clear();
        machineService.getTicketMachine().getCustomerCoinSlot().getObjectNumber().clear();
        machineService.getTicketMachine().getCustomerCoinSlot().getLimitObjectNumber().clear();
    }

    private void cancelBuy(Model model) {
        model.add("customerCoins", machineService.getTicketMachine().getCustomerCoinSlot().getObjectNumber());
        showPage(new CancelBuyPage(model));
        clearBuy(model);
        back2HomePage();
    }

    private boolean isAddedCoinBelowLSlotLimit(BigDecimal coin) {
        CoinSlot slot = machineService.getTicketMachine().getMachineCoinSlot();
        Nominal nominal = machineService.createNominal(coin);
        return slot.getNumber(nominal) + machineService.getTicketMachine().getCustomerCoinSlot().getNumber(nominal) < slot.getLimitObjectNumber(nominal);
    }


    public static final class SingletonHolder {
        private static final MachineController MACHINE_CONTROLLER = new MachineController();

        public static MachineController getInstance() {
            return MACHINE_CONTROLLER;
        }
    }
}

