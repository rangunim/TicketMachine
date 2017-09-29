package net.mstezala.mvc.controllers;

import net.mstezala.configurations.ApplicationConfiguration;
import net.mstezala.mvc.models.Model;
import net.mstezala.mvc.models.ShowTicketModel;
import net.mstezala.mvc.pages.console.Home.PriceListPage;
import net.mstezala.mvc.pages.console.Shared.CancelBuyPage;
import net.mstezala.mvc.pages.console.Ticket.ChooseTicketTypePage;
import net.mstezala.mvc.pages.console.Ticket.DiscountedTicketQueryPage;
import net.mstezala.mvc.pages.console.Ticket.NumberTicketQueryPage;
import net.mstezala.mvc.pages.console.Ticket.TicketListPage;
import net.mstezala.services.TicketService;
import net.mstezala.services.models.Ticket;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketController extends AbstractController {

    private final TicketService ticketService;
    private final Model sessionModel;

    private TicketController() {
        ticketService = ApplicationConfiguration.ticketService;
        sessionModel = new Model();
    }


    public void priceList() {
        Collection<Ticket> tickets = ticketService.getSingleTypeTickets();
        tickets.addAll(ticketService.getTimeTypeTickets());
        tickets = tickets.parallelStream().sorted((x1, x2) -> x1.getType().name().compareTo(x2.getType().name())).collect(Collectors.toList());
        Model model = new Model();
        model.add("tickets", tickets); //TODO znizka!!
        showPage(new PriceListPage(model));
        ApplicationConfiguration.homeController.index();
    }

    public void chooseTicketType() {
        showPage(new ChooseTicketTypePage());
        chooseTicketTypePOST(getCustomerAction());
    }

    private void chooseTicketTypePOST(int userAction) {
        boolean endLocalLoop = false;
        while (!endLocalLoop) {
            switch (userAction) {
                case 0: {
                    showPage(new CancelBuyPage());
                    back2HomePage();
                    endLocalLoop = true;
                    break;
                }
                case 1: {
                    ShowTicketModel model = new ShowTicketModel();
                    model.setTicketMap((Map<Integer, Ticket>) convertCollect2Map(ticketService.getSingleTypeTickets()));
                    showTicketList(model);
                    endLocalLoop = true;
                    break;
                }
                case 2: {
                    ShowTicketModel model = new ShowTicketModel();
                    model.setTicketMap((Map<Integer, Ticket>) convertCollect2Map(ticketService.getTimeTypeTickets()));
                    showTicketList(model);
                    endLocalLoop = true;
                    break;
                }
                case 3: {
                    showPage(new CancelBuyPage());
                    endLocalLoop = true;
                    break;
                }
                default: {
                    userAction = onWrongChoice(3);
                    break;
                }
            }
        }
    }

    public void showTicketList(Model model) {
        showPage(new TicketListPage(model));
        showTicketListPOST(model, getCustomerAction());
    }

    private void showTicketListPOST(Model model, int userAction) {
        if (userAction == 0) {
            showPage(new CancelBuyPage());
            back2HomePage();
            return;
        }

        ShowTicketModel showTicketModel = (ShowTicketModel) model;
        if (userAction == showTicketModel.getTicketMap().size() + 1) {
            chooseTicketType();
            return;
        }

        while (userAction < 1 || userAction > showTicketModel.getTicketMap().size()) {
            userAction = onWrongChoice(showTicketModel.getTicketMap().size());
        }
        sessionModel.add("chosenTicket", showTicketModel.getTicketMap().get(userAction));
        discountedTicketQuery();
    }


    public void discountedTicketQuery() {
        showPage(new DiscountedTicketQueryPage());
        discountedTicketQueryPOST(getCustomerAction());
    }

    private void discountedTicketQueryPOST(int userAction) {
        boolean endLocalLoop = false;
        while (!endLocalLoop) {
            switch (userAction) {
                case 0: {
                    showPage(new CancelBuyPage());
                    back2HomePage();
                    endLocalLoop = true;
                    break;
                }
                case 1: {
                    numberTicketQuery();
                    endLocalLoop = true;
                    break;
                }
                case 2: {
                    ticketService.setStandardValueDiscountTicketPrice((Ticket) sessionModel.get("chosenTicket"));
                    numberTicketQuery();
                    endLocalLoop = true;
                    break;
                }
                case 3: {
                    chooseTicketType();
                    endLocalLoop = true;
                    break;
                }
                default: {
                    userAction = onWrongChoice(3);
                    break;
                }
            }
        }
    }

    public void numberTicketQuery() {
        showPage(new NumberTicketQueryPage());
        numberTicketQueryPOST(getCustomerAction());
    }

    private void numberTicketQueryPOST(int userAction) {
        while (userAction < 1 || userAction > 10) {
            userAction = onWrongChoice(10);
        }
        Ticket chosenTicket = (Ticket) sessionModel.get("chosenTicket");
        if (ApplicationConfiguration.ticketService.getTicketSlot().getNumber(chosenTicket) < userAction) {
            System.out.println("W automacie brakuje biletów " + (chosenTicket.getType().name().equalsIgnoreCase("SINGLE") ? "[Jednorazowy]" : "[Czasowy]") + chosenTicket.getName());
            showPage(new CancelBuyPage());
            sessionModel.clear();
            back2HomePage();
        } else {
            sessionModel.add("numberTicketChosen", userAction);
            ApplicationConfiguration.machineController.buyTicket(sessionModel);
        }
    }

    public static final class SingletonHolder {
        private static final TicketController TICKET_CONTROLLER = new TicketController();

        public static TicketController getInstance() {
            return TICKET_CONTROLLER;
        }
    }
}
