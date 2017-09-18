package net.mstezala.mvc.models;

public class MachineStateModel extends Model {
    private String infoAboutTickets;
    private String infoAboutMachineCoins;
    private String infoAboutCustomerCoins;

    public String getInfoAboutTickets() {
        return infoAboutTickets;
    }

    public void setInfoAboutTickets(String infoAboutTickets) {
        this.infoAboutTickets = infoAboutTickets;
    }

    public String getInfoAboutMachineCoins() {
        return infoAboutMachineCoins;
    }

    public void setInfoAboutMachineCoins(String infoAboutMachineCoins) {
        this.infoAboutMachineCoins = infoAboutMachineCoins;
    }

    public String getInfoAboutCustomerCoins() {
        return infoAboutCustomerCoins;
    }

    public void setInfoAboutCustomerCoins(String infoAboutCustomerCoins) {
        this.infoAboutCustomerCoins = infoAboutCustomerCoins;
    }
}
