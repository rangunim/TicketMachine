package net.mstezala;

import net.mstezala.configurations.ApplicationConfiguration;

public class TicketMachineApplication {
    /**
     * Run the application
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationConfiguration.machineService.setStandardConfiguration();
        ApplicationConfiguration.homeController.index();
    }
}
