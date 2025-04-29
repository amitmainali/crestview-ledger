package com.pluralsight;

import static com.pluralsight.CrestviewDisplay.vanityScreen;
import static com.pluralsight.LedgerApp.showHomeScreen;

public class Main {
    public static boolean running = true;

    public static void main(String[] args) {
        vanityScreen();

        while (running) {
            running = showHomeScreen();
        }

    }
}