package programming;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
            System.out.println("Transfer successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid transfer amount or insufficient funds.");
        }
    }
}

class ATMSimulator {
    public static void main(String[] args) {
        Map<String, BankAccount> accounts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("ATM Simulator");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character.

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    BankAccount account = new BankAccount(accountNumber);
                    accounts.put(accountNumber, account);
                    System.out.println("Account created successfully.");
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    if (accounts.containsKey(accountNumber)) {
                        System.out.print("Enter Deposit Amount: $");
                        double depositAmount = scanner.nextDouble();
                        accounts.get(accountNumber).deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    if (accounts.containsKey(accountNumber)) {
                        System.out.print("Enter Withdrawal Amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        accounts.get(accountNumber).withdraw(withdrawalAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Your Account Number: ");
                    String senderAccountNumber = scanner.nextLine();
                    System.out.print("Enter Recipient Account Number: ");
                    String recipientAccountNumber = scanner.nextLine();
                    if (accounts.containsKey(senderAccountNumber) && accounts.containsKey(recipientAccountNumber)) {
                        System.out.print("Enter Transfer Amount: $");
                        double transferAmount = scanner.nextDouble();
                        accounts.get(senderAccountNumber).transfer(accounts.get(recipientAccountNumber), transferAmount);
                    } else {
                        System.out.println("One or both accounts not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    if (accounts.containsKey(accountNumber)) {
                        double balance = accounts.get(accountNumber).getBalance();
                        System.out.println("Balance: $" + balance);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting ATM Simulator.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
