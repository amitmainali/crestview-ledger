package com.pluralsight;

import static com.pluralsight.CrestviewDisplay.vanityScreen;
import static com.pluralsight.InputHelper.makePayment;
import static com.pluralsight.LedgerApp.showHomeScreen;

public class Main {
    public static void main(String[] args) {
        vanityScreen();
        showHomeScreen();
        makePayment();

    }
}
