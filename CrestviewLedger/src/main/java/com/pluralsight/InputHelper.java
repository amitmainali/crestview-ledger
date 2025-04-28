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

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        String date = LocalDate.now().toString();
        String time = LocalTime.now().withNano(0).toString(); // remove nanoseconds


        String newEntry = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/transactions.csv", true))) {
            writer.newLine();
            writer.write(newEntry);
            System.out.println("Deposit added successfully!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
