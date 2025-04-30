package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class InputHelper {

    public static void addDeposit() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            String date = LocalDate.now().toString();
            String time = LocalTime.now().withNano(0).toString();

            Transaction transaction = new Transaction(date, time, description, vendor, amount);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/transactions.csv", true))) {
                writer.newLine();
                writer.write(transaction.toString());
                System.out.println("Deposit added successfully!");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("\n\nPress Enter to continue...\n");
            scanner.nextLine();

            System.out.println("\n=================================================================");
            System.out.println("What would you like to do next?");
            System.out.println("\t1) Add another deposit");
            System.out.println("\t2) Go to Home Screen");
            System.out.println("\t3) Exit Application");
            System.out.print("\nEnter your choice: ");
            String nextChoice = scanner.nextLine();

            switch (nextChoice) {
                case "1":
                    continue;
                case "2":
                    return;
                case "3":
                    System.out.println("Exiting Crestview Ledger. Goodbye!");
                    Main.running = false;
                    return;
                default:
                    System.out.println("Invalid input. Returning to Home Screen.\n");
                    return;
            }
        }
    }

    public static void makePayment() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter payment amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount > 0) {
                amount = -amount;
            }

            String date = LocalDate.now().toString();
            String time = LocalTime.now().withNano(0).toString();

            Transaction transaction = new Transaction(date, time, description, vendor, amount);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/transactions.csv", true))) {
                writer.newLine();
                writer.write(transaction.toString());
                System.out.println("Payment recorded successfully!");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("\n\nPress Enter to continue...\n");
            scanner.nextLine();

            System.out.println("\n=================================================================");
            System.out.println("What would you like to do next?");
            System.out.println("\t1) Make another payment");
            System.out.println("\t2) Go to Home Screen");
            System.out.println("\t3) Exit Application");
            System.out.print("\nEnter your choice: ");
            String nextChoice = scanner.nextLine();

            switch (nextChoice) {
                case "1":
                    continue;
                case "2":
                    return;
                case "3":
                    System.out.println("Exiting Crestview Ledger. Goodbye!");
                    Main.running = false;
                    return;
                default:
                    System.out.println("Invalid input. Returning to Home Screen.\n");
                    return;
            }
        }
    }
}