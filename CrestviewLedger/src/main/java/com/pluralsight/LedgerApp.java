package com.pluralsight;

import java.util.Scanner;

public class LedgerApp {
    public static void showHomeScreen() {
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
                // TODO: Call method to add a deposit
                System.out.println("Add Deposit selected.");
                break;
            case "P":
                // TODO: Call method to make a payment
                System.out.println("Make Payment selected.");
                break;
            case "L":
                // TODO: Call method to view ledger
                System.out.println("View Ledger selected.");
                break;
            case "X":
                System.out.println("Exiting Crestview Ledger. Goodbye!");
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
        }
    }
}
