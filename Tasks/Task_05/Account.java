package ElevateLabs.Tasks.Task_05;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private List<String> transactionHistory;

    public Account() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
        } else {
            transactionHistory.add("Withdrawal failed for: $" + amount);
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}