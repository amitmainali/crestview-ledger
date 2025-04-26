package com.pluralsight;

import java.time.LocalTime;
import java.util.Scanner;

    public class CrestviewDisplay {

        public static void vanityScreen() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("-----------------------------------------------------------------");
            System.out.println("                   Welcome to Crestview Ledger");
            System.out.println("-----------------------------------------------------------------\n");

            System.out.println("                          /\\");
            System.out.println("                         /  \\");
            System.out.println("                        /    \\   /\\");
            System.out.println("                       /      \\ /  \\   /\\");
            System.out.println("                      /        /    \\ /  \\");
            System.out.println("                    ------------------------");

            LocalTime now = LocalTime.now();
            if (now.isBefore(LocalTime.NOON)) {
                System.out.println("\n                          Good Morning");
            } else if (now.isBefore(LocalTime.of(17, 0))) {
                System.out.println("\n                          Good Afternoon");
            } else {
                System.out.println("\n                          Good Evening");
            }

            System.out.println("\n                    Press ENTER to continue...");
            scanner.nextLine();
        }
    }


