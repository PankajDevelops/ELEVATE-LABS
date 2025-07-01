package ElevateLabs.Tasks.Task_05;

public class Main {
    public static void main(String[] args) {
        Account myAccount = new Account();

        myAccount.deposit(500.00);
        myAccount.withdraw(200.00);
        myAccount.deposit(150.00);
        myAccount.withdraw(600.00); // This will fail

        System.out.println("Transaction History:");
        for (String transaction : myAccount.getTransactionHistory()) {
            System.out.println(transaction);
        }

        System.out.println("\nFinal Balance: $" + myAccount.getBalance());
    }
}