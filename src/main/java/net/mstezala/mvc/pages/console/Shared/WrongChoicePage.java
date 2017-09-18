package net.mstezala.mvc.pages.console.Shared;

import net.mstezala.mvc.pages.Page;

public class WrongChoicePage implements Page {

    @Override
    public void show() {
        System.out.println("\n \t WYBRANO NIEPOPRAWNĄ OPCJĘ! \n");
    }
}
