package com.pluralsight;

import java.util.Scanner;

public class LedgerApp {
    public static boolean showHomeScreen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n========================== Home Screen ==========================");
        System.out.println("\tD) Add Deposit");
        System.out.println("\tP) Make Payment (Debit)");
        System.out.println("\tL) View Ledger");
        System.out.println("\tX) Exit");
        System.out.print("\nEnter your choice: ");

        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "D":
                System.out.println("Add Deposit selected.\n");
                InputHelper.addDeposit();
                break;
            case "P":
                System.out.println("Make Payment selected.\n");
                InputHelper.makePayment();
                break;
            case "L":
                System.out.println("View Ledger selected.\n");
                LedgerView.showLedgerScreen();
                break;
            case "X":
                System.out.println("Exiting Crestview Ledger. Goodbye!");
                return false;
            default:
                System.out.println("Invalid selection. Please try again.\n");
        }

        if(!Main.running) {
            return false;
        }

        return true;
    }
}