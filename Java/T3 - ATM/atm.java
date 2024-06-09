import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class atm {
    private static final int PIN = 9718;
    private static double balance = 10000.00;
    private static int userID = 5822;
    private static int recipientID;
    private static ArrayList<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\nWelcome to the ATM!");
        System.out.println("\nEnter your user ID:");
        int user = scanner.nextInt();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\nEnter your PIN:");
        int userPin = scanner.nextInt();

        if (user != userID) {
            System.out.println("\nThe entered userID does not exist. Please try again.");
            scanner.close();
            return;
        }
        
        if (userPin != PIN) {
            System.out.println("\nIncorrect PIN. Please try again.");
            scanner.close();
            return;
        }

        while (true) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");            
            System.out.println("\nSelect an option:\n");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    displayTransactionHistory();
                    break;
                case 6:
                    System.out.println("\nThank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");        
        System.out.println("\nYour current balance is: Rs." + balance);
        transactionHistory.add(LocalDate.now() + "   |   " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "   |   Checked balance: Rs." + balance);
    }

    private static void deposit(Scanner scanner) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");                
        System.out.println("\nEnter the amount you want to deposit:");
        double amount = scanner.nextDouble();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");        
        balance += amount;
        System.out.println("\nAmount deposited: Rs." + amount);
        System.out.println("\nYour new balance is: Rs." + balance);
        transactionHistory.add(LocalDate.now() + "   |   " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "   |   Deposit: Rs." + amount);
    }
    
    private static void withdraw(Scanner scanner) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");        
        System.out.println("\nEnter the amount you want to withdraw: ");
        double amount = scanner.nextDouble();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if (amount > balance) {
            System.out.println("\nInsufficient funds.");
            transactionHistory.add(LocalDate.now() + "   |   " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "   |   Withdrawal: Failed attempt, Reason: Insufficient funds");
        } else {
            balance -= amount;
            System.out.println("\nAmount withdrawn: Rs." + amount);
            System.out.println("\nYour new balance is: Rs." + balance);
            transactionHistory.add(LocalDate.now() + "   |   " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "   |   Withdrawal: Rs." + amount);
        }
    }

    private static void transfer(Scanner scanner) {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");                
        System.out.println("\nEnter the recipient's user ID:");
        recipientID = scanner.nextInt();
        System.out.println("\nEnter the amount to transfer:");
        double amount = scanner.nextDouble();
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");        
        if (amount > balance) {
            System.out.println("\nInsufficient funds.");
            transactionHistory.add(LocalDate.now() + "   |   " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "   |   Transfer: Failed attempt, Reason: Insufficient funds");
        } else {
            balance -= amount;
            System.out.println("\nAmount transferred: Rs." + amount + " to user ID: " + recipientID);
            System.out.println("\nYour new balance is: Rs." + balance);
            transactionHistory.add(LocalDate.now() + "   |   " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "   |   Transfer: Rs." + amount + " to user ID: " + recipientID);
        }
    }

    private static void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");                
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}