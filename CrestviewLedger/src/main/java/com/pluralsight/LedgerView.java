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
                displayAllEntries();
                break;
            case "D":
                displayDeposits();
                break;
            case "P":
                displayPayments();
                break;
            case "R":
//                ReportGenerator.showReportScreen();
                break;
            case "H":
                LedgerApp.showHomeScreen();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void displayAllEntries() {
        ArrayList<String> transactions = loadTransactions();
        sortTransactions(transactions);

        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
    }

    public static void displayDeposits() {
        ArrayList<String> transactions = loadTransactions();
        sortTransactions(transactions);

        for (int i = 0; i < transactions.size(); i++) {
            String[] parts = transactions.get(i).split("\\|");
            double amount = Double.parseDouble(parts[4]);
            if (amount > 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    public static void displayPayments() {
        ArrayList<String> transactions = loadTransactions();
        sortTransactions(transactions);

        for (int i = 0; i < transactions.size(); i++) {
            String[] parts = transactions.get(i).split("\\|");
            double amount = Double.parseDouble(parts[4]);
            if (amount < 0) {
                System.out.println(transactions.get(i));
            }
        }
    }

    private static ArrayList<String> loadTransactions() {
        ArrayList<String> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return transactions;
    }

    private static void sortTransactions(ArrayList<String> transactions) {
        Collections.sort(transactions);
        Collections.reverse(transactions);
    }
}