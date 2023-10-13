import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    
    public String toString() {
        return "Type: " + type + ", Amount: $" + amount;
    }
}

class Account {
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Account() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("DEPOSIT", amount));
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("WITHDRAW", amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(Account toAccount, double amount) {
        if (balance >= amount) {
            balance -= amount;
            toAccount.deposit(amount);
            transactionHistory.add(new Transaction("TRANSFER", amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();

        while (true) {
            System.out.println("ATM MENU:");
            System.out.println("1.TRANSACTION HISTORY");
            System.out.println("2. WITHDRAW");
            System.out.println("3. DEPOSIT");
            System.out.println("4. TRANSFER");
            System.out.println("5. QUIT");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:                    
                System.out.print("TRANSACTION HISTORY:");
                     ArrayList<Transaction> history = account.getTransactionHistory();
                     for (Transaction transaction : history) {
                     System.out.println(transaction);
                     }
                     break;   
                case 2:
                     System.out.print("Enter withdrawal amount: $");
                     double withdrawalAmount = scanner.nextDouble();
                     account.withdraw(withdrawalAmount);
                     break;
                case 3:
                     System.out.print("Enter deposit amount: $");
                     double depositAmount = scanner.nextDouble();
                     account.deposit(depositAmount);
                     break;
                    
                case 4:
                     System.out.print("Enter transfer amount: $");
                     double transferAmount = scanner.nextDouble();
                     System.out.print("Enter recipient's account balance: $");
                     Account recipientAccount = new Account();
                     recipientAccount.deposit(scanner.nextDouble());
                     account.transfer(recipientAccount, transferAmount);    
                     break;
                case 5:
                     System.out.println("THANK YOUU!!!");
                     System.exit(0);
                     break;
                default:
                     System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}