package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LedgerView {

    public static void showLedgerScreen() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("\n========================== Ledger Menu ==========================");
        System.out.println("\tA) View All Entries");
        System.out.println("\tD) View Deposits Only");
        System.out.println("\tP) View Payments Only");
        System.out.println("\tR) Reports");
        System.out.println("\tH) Home");
        System.out.print("\nEnter your choice: ");
        choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "A":
                System.out.println("View All Entries selected.\n");
                displayAllEntries();
                break;
            case "D":
                System.out.println("View Deposits selected.\n");
                displayDeposits();
                break;
            case "P":
                System.out.println("View Payments selected.\n");
                displayPayments();
                break;
            case "R":
                System.out.println("View Reports selected.");
                ReportGenerator.showReportScreen();
                break;
            case "H":
                System.out.println("Return Home selected.");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void displayAllEntries() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ArrayList<Transaction> transactions = loadTransactions();
            Collections.reverse(transactions);

            for (int i = 0; i < transactions.size(); i++) {
                System.out.println(transactions.get(i));
            }

            System.out.print("\n\nPress Enter to continue...\n");
            scanner.nextLine();

            System.out.println("\n=================================================================");
            System.out.println("What would you like to do next?");
            System.out.println("\t1) View All Entries Again");
            System.out.println("\t2) Go Back to Ledger Menu");
            System.out.println("\t3) Go to Home Screen");
            System.out.println("\t4) Exit Application");
            System.out.print("\nEnter your choice: ");
            String nextChoice = scanner.nextLine();

            switch (nextChoice) {
                case "1":
                    continue;
                case "2":
                    showLedgerScreen();
                    return;
                case "3":
                    return;
                case "4":
                    System.out.println("Exiting Crestview Ledger. Goodbye!");
                    Main.running = false;
                    return;
                default:
                    System.out.println("Invalid input. Returning to Ledger Menu.\n");
                    showLedgerScreen();
                    return;
            }
        }
    }

    public static void displayDeposits() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ArrayList<Transaction> transactions = loadTransactions();
            Collections.reverse(transactions);

            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).getAmount() > 0) {
                    System.out.println(transactions.get(i));
                }
            }

            System.out.print("\n\nPress Enter to continue...\n");
            scanner.nextLine();

            System.out.println("\n=================================================================");
            System.out.println("What would you like to do next?");
            System.out.println("\t1) View Deposits Again");
            System.out.println("\t2) Go Back to Ledger Menu");
            System.out.println("\t3) Go to Home Screen");
            System.out.println("\t4) Exit Application");
            System.out.print("\nEnter your choice: ");
            String nextChoice = scanner.nextLine();

            switch (nextChoice) {
                case "1":
                    continue;
                case "2":
                    showLedgerScreen();
                    return;
                case "3":
                    return;
                case "4":
                    System.out.println("Exiting Crestview Ledger. Goodbye!");
                    Main.running = false;
                    return;
                default:
                    System.out.println("Invalid input. Returning to Ledger Menu.\n");
                    showLedgerScreen();
                    return;
            }
        }
    }

    public static void displayPayments() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ArrayList<Transaction> transactions = loadTransactions();
            Collections.reverse(transactions);

            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).getAmount() < 0) {
                    System.out.println(transactions.get(i));
                }
            }

            System.out.print("\n\nPress Enter to continue...\n");
            scanner.nextLine();

            System.out.println("\n=================================================================");
            System.out.println("What would you like to do next?");
            System.out.println("\t1) View Payments Again");
            System.out.println("\t2) Go Back to Ledger Menu");
            System.out.println("\t3) Go to Home Screen");
            System.out.println("\t4) Exit Application");
            System.out.print("\nEnter your choice: ");
            String nextChoice = scanner.nextLine();

            switch (nextChoice) {
                case "1":
                    continue;
                case "2":
                    showLedgerScreen();
                    return;
                case "3":
                    return;
                case "4":
                    System.out.println("Exiting Crestview Ledger. Goodbye!");
                    Main.running = false;
                    return;
                default:
                    System.out.println("Invalid input. Returning to Ledger Menu.\n");
                    showLedgerScreen();
                    return;
            }
        }
    }

    private static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date, time, description, vendor, amount));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return transactions;
    }
}