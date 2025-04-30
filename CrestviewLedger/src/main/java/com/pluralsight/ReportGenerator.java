package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportGenerator {

    private static final String filePath = "data/transactions.csv";
    private static final DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void showReportScreen() {
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("\n========================== Reports Menu =========================");
            System.out.println("\t1) Month To Date");
            System.out.println("\t2) Previous Month");
            System.out.println("\t3) Year To Date");
            System.out.println("\t4) Previous Year");
            System.out.println("\t5) Search by Vendor");
            System.out.println("\t6) Custom Search");
            System.out.println("\t0) Back to Ledger");
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    reportMonthToDate();
                    break;
                case "2":
                    reportPreviousMonth();
                    break;
                case "3":
                    reportYearToDate();
                    break;
                case "4":
                    reportPreviousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "6":
                    customSearch();
                    break;
                case "0":
                    LedgerView.showLedgerScreen();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            System.out.print("\n\nPress Enter to continue...\n\n");
            scanner.nextLine();

            System.out.println("=================================================================");
            System.out.println("What would you like to do next?");
            System.out.println("\t1) View Another Report");
            System.out.println("\t2) Go Back to Ledger Menu");
            System.out.println("\t3) Go to Home Screen");
            System.out.println("\t4) Exit Application");
            System.out.print("\nEnter your choice: ");
            String nextChoice = scanner.nextLine();

            switch (nextChoice) {
                case "1":
                    continue;
                case "2":
                    LedgerView.showLedgerScreen();
                    return;
                case "3":
                    return;
                case "4":
                    System.out.println("Exiting Crestview Ledger. Goodbye!");
                    Main.running = false;
                    return;
                default:
                    System.out.println("Invalid input. Returning to Reports Menu.");
            }
        }
    }

    private static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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

    private static void reportMonthToDate() {
        ArrayList<Transaction> transactions = loadTransactions();
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int year = today.getYear();

        System.out.println("\nMonth to Date Report");
        System.out.println("=================================================================");

        for (int i = 0; i < transactions.size(); i++) {
            LocalDate txDate = LocalDate.parse(transactions.get(i).getDate(), customFormatter);
            if (txDate.getYear() == year && txDate.getMonthValue() == month) {
                System.out.println(transactions.get(i));
            }
        }
    }

    private static void reportPreviousMonth() {
        ArrayList<Transaction> transactions = loadTransactions();
        LocalDate today = LocalDate.now();
        LocalDate previousMonth = today.minusMonths(1);
        int month = previousMonth.getMonthValue();
        int year = previousMonth.getYear();

        System.out.println("\nPrevious Month Report");
        System.out.println("=================================================================");

        for (int i = 0; i < transactions.size(); i++) {
            LocalDate txDate = LocalDate.parse(transactions.get(i).getDate(), customFormatter);
            if (txDate.getYear() == year && txDate.getMonthValue() == month) {
                System.out.println(transactions.get(i));
            }
        }
    }

    private static void reportYearToDate() {
        ArrayList<Transaction> transactions = loadTransactions();
        int year = LocalDate.now().getYear();

        System.out.println("\nYear to Date Report");
        System.out.println("=================================================================");

        for (int i = 0; i < transactions.size(); i++) {
            LocalDate txDate = LocalDate.parse(transactions.get(i).getDate(), customFormatter);
            if (txDate.getYear() == year) {
                System.out.println(transactions.get(i));
            }
        }
    }

    private static void reportPreviousYear() {
        ArrayList<Transaction> transactions = loadTransactions();
        int prevYear = LocalDate.now().getYear() - 1;

        System.out.println("\nPrevious Year Report");
        System.out.println("=================================================================");

        for (int i = 0; i < transactions.size(); i++) {
            LocalDate txDate = LocalDate.parse(transactions.get(i).getDate(), customFormatter);
            if (txDate.getYear() == prevYear) {
                System.out.println(transactions.get(i));
            }
        }
    }

    private static void searchByVendor() {
        ArrayList<Transaction> transactions = loadTransactions();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter vendor name to search for: ");
        String input = scanner.nextLine().trim().toLowerCase();

        System.out.println("\nSearch Results");
        System.out.println("=================================================================");

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getVendor().toLowerCase().contains(input)) {
                System.out.println(transactions.get(i));
            }
        }
    }

    private static void customSearch() {
        ArrayList<Transaction> transactions = loadTransactions();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter start date (yyyy-MM-dd) or leave blank: ");
        String startInput = scanner.nextLine().trim();

        System.out.print("Enter end date (yyyy-MM-dd) or leave blank: ");
        String endInput = scanner.nextLine().trim();

        System.out.print("Enter description keyword or leave blank: ");
        String descInput = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter vendor keyword or leave blank: ");
        String vendorInput = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter exact amount or leave blank: ");
        String amountInput = scanner.nextLine().trim();

        System.out.println("\nCustom Search Results");
        System.out.println("=================================================================");

        for (int i = 0; i < transactions.size(); i++) {
            Transaction tx = transactions.get(i);
            boolean match = true;

            LocalDate txDate = LocalDate.parse(tx.getDate(), customFormatter);

            if (!startInput.isEmpty()) {
                LocalDate start = LocalDate.parse(startInput, customFormatter);
                if (txDate.isBefore(start)) {
                    match = false;
                }
            }

            if (!endInput.isEmpty()) {
                LocalDate end = LocalDate.parse(endInput, customFormatter);
                if (txDate.isAfter(end)) {
                    match = false;
                }
            }

            if (!descInput.isEmpty() && !tx.getDescription().toLowerCase().contains(descInput)) {
                match = false;
            }

            if (!vendorInput.isEmpty() && !tx.getVendor().toLowerCase().contains(vendorInput)) {
                match = false;
            }

            if (!amountInput.isEmpty()) {
                double inputAmount = Double.parseDouble(amountInput);
                if (tx.getAmount() != inputAmount) {
                    match = false;
                }
            }

            if (match) {
                System.out.println(tx);
            }
        }
    }
}