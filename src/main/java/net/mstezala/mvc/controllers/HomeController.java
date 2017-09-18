package net.mstezala.mvc.controllers;

import net.mstezala.configurations.ApplicationConfiguration;
import net.mstezala.mvc.models.MachineStateModel;
import net.mstezala.mvc.pages.console.Home.ClosePage;
import net.mstezala.mvc.pages.console.Home.HomePage;
import net.mstezala.mvc.pages.console.Home.TicketMachineStatePage;

public class HomeController extends AbstractController {
    private HomeController() {
    }

    public void index() {
        showPage(new HomePage());
        indexPOST(getCustomerAction());
    }

    public void indexPOST(int userAction) {
        boolean endLocalLoop = false;
        while (!endLocalLoop) {
            switch (userAction) {
                case 0: {
                    closeApplication();
                    showPage(new ClosePage());
                    endLocalLoop = true;
                    break;
                }
                case 1: {
                    ApplicationConfiguration.ticketController.chooseTicketType();
                    endLocalLoop = true;
                    break;
                }
                case 2: {
                    ApplicationConfiguration.ticketController.priceList();
                    endLocalLoop = true;
                    break;
                }
                case 3: {
                    ApplicationConfiguration.machineController.getInfo();
                    back2HomePage();
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

    private void closeApplication() {
        scanner4userAction.close();
    }

    public static final class SingletonHolder {
        private static final HomeController HOME_CONTROLLER = new HomeController();

        public static HomeController getInstance() {
            return HOME_CONTROLLER;
        }
    }
}
