package net.mstezala.mvc.pages.console.Home;

import net.mstezala.mvc.pages.Page;

public class ClosePage implements Page {

    @Override
    public void show() {
        System.out.println("\nDziekujemy za skorzystanie z naszych usług.");
    }
}
