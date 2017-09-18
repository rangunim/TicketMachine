package net.mstezala.mvc.pages.console.Home;


import net.mstezala.mvc.pages.Page;

public class HomePage implements Page {
    public void show() {
        System.out.println("\n####### Witamy w naszym automacie biletów #######");
        System.out.println("Co chcesz zrobić? ");
        System.out.println("1) Kupić bilet");
        System.out.println("2) Zobaczyć cennik");
        System.out.println("3) Sprawdzić aktualny stan maszyny");
        System.out.println("0) Wyjście");
        System.out.print("Twój wybór: ");
    }
}
