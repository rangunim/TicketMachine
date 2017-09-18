package net.mstezala.mvc.controllers;

import net.mstezala.configurations.ApplicationConfiguration;
import net.mstezala.mvc.pages.Page;
import net.mstezala.mvc.pages.console.Shared.WrongChoicePage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class AbstractController {
    protected Page lastPage;
    protected static Scanner scanner4userAction = new Scanner(System.in);

    protected int getCustomerAction() {
        int action = -1;
        while (action < 0) {
            try {
                action = scanner4userAction.nextInt();
            } catch (Exception ex) {
                onWrongChoice();
                scanner4userAction = new Scanner(System.in);
                action = -1;
            }
        }
        return action;
    }

    protected void onWrongChoice() {
        new WrongChoicePage().show();
        showLastPage();
    }

    protected int onWrongChoice(int maxRange) {
        int result = -1;
        while (result < 0 || result > maxRange) {
            onWrongChoice();
            result = getCustomerAction();
        }
        return result;
    }

    protected void showPage(Page page) {
        lastPage = page;
        showLastPage();
    }

    protected void showLastPage() {
        if (lastPage != null) {
            lastPage.show();
        }
    }

    protected void back2HomePage() {
        ApplicationConfiguration.homeController.index();
    }


    protected static Map<Integer, ? extends Object> convertCollect2Map(Collection<? extends Object> collection) {
        Map<Integer, Object> result = new HashMap<>(collection.size());
        int optionNumber = 1;
        for (Object ticket : collection) {
            result.put(optionNumber++, ticket);
        }
        return result;
    }


}
