# Crestview Ledger

Crestview Ledger is a command-line accounting application written in Java. It allows users to track deposits and payments, view a transaction ledger, and generate detailed reports — all from a simple, keyboard-driven interface.

## 🔍 Project Overview

This project was developed as part of a capstone assignment to demonstrate core Java programming skills including:

- File input/output
- CLI menu navigation
- Object-oriented design
- Input validation and looping control
- Real-world application modeling

The application reads and writes transaction data to a `transactions.csv` file and organizes information using a custom-built ledger system.

## 📸 Screenshots

### Vanity Screen
![Screenshot 2025-04-30 162903](https://github.com/user-attachments/assets/1d506526-f812-4fef-8fac-8d6eec532a8e)


### Home Screen
![Screenshot 2025-04-30 162926](https://github.com/user-attachments/assets/cc72e471-c68a-4eb7-b059-97bd76e5dc69)


### Ledger Menu
![Screenshot 2025-04-30 162958](https://github.com/user-attachments/assets/72ec94e4-e1b3-4920-9368-06a561dbeb00)


### Reports Menu
![Screenshot 2025-04-30 163013](https://github.com/user-attachments/assets/f82566a6-9195-435b-a1a1-20efd2fcf486)



## 💡 Interesting Feature: Refactoring with Transaction Objects

Originally, the application parsed each line of the `transactions.csv` file as a raw string, manually splitting values with `split("\\|")` and accessing each array index. This worked, but was difficult to scale, maintain, or understand at a glance.

To solve this, the program was refactored to use a custom `Transaction` object to represent each row as a structured unit of data:

```java
public class Transaction {
    private String date, time, description, vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String toString() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount);
    }
}
```

This allowed the entire codebase to shift from string-based logic to clean, object-oriented loops like:

```java
for (Transaction tx : transactions) {
    if (tx.getAmount() > 0) {
        System.out.println(tx);
    }
}
```

It made filtering, displaying, and formatting transactions much easier and helped reduce repeated logic.

## ✅ Technologies Used

- Java
- IntelliJ IDEA
- CSV for persistent data
- Git & GitHub for version control

## 🚀 Getting Started

1. Clone the repository:
   ```
   git clone https://github.com/amitmainali/crestview-ledger.git
   ```

2. Open the project in IntelliJ IDEA.

3. Run the `Main.java` file.

4. Follow the on-screen menu prompts to interact with the application.

5. To understand the file format or create sample data, open `data/transactions.csv`.

---
