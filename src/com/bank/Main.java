package com.bank;

import com.bank.model.SavingsAccount;
import com.bank.model.CurrentAccount;
import com.bank.repository.AccountRepository;
import com.bank.repository.AccountRepositoryImpl;
import com.bank.repository.DatabaseConnectionManager;
import com.bank.repository.TransactionRepository;
import com.bank.repository.TransactionRepositoryImpl;
import com.bank.service.BankService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize DB
        DatabaseConnectionManager.initializeDatabase();

        // DI Setup
        AccountRepository accountRepo = new AccountRepositoryImpl();
        TransactionRepository transactionRepo = new TransactionRepositoryImpl();
        BankService bankService = new BankService(accountRepo, transactionRepo);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Welcome to the Banking Management System ===");

        while (true) {
            System.out.println("\n1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Check Balance");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Account Number: ");
                        String sAccNum = scanner.nextLine();
                        System.out.print("Enter Customer ID: ");
                        String sCustId = scanner.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        double sBal = scanner.nextDouble();
                        bankService.createAccount(new SavingsAccount(sAccNum, sCustId, sBal));
                        System.out.println("Savings Account created successfully.");
                        break;
                    case 2:
                        System.out.print("Enter Account Number: ");
                        String cAccNum = scanner.nextLine();
                        System.out.print("Enter Customer ID: ");
                        String cCustId = scanner.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        double cBal = scanner.nextDouble();
                        bankService.createAccount(new CurrentAccount(cAccNum, cCustId, cBal));
                        System.out.println("Current Account created successfully.");
                        break;
                    case 3:
                        System.out.print("Enter Account Number: ");
                        String depAccNum = scanner.nextLine();
                        System.out.print("Enter Amount to Deposit: ");
                        double depAmt = scanner.nextDouble();
                        bankService.deposit(depAccNum, depAmt, "Cash Deposit");
                        System.out.println("Deposit successful.");
                        break;
                    case 4:
                        System.out.print("Enter Account Number: ");
                        String withAccNum = scanner.nextLine();
                        System.out.print("Enter Amount to Withdraw: ");
                        double withAmt = scanner.nextDouble();
                        bankService.withdraw(withAccNum, withAmt, "Cash Withdrawal");
                        System.out.println("Withdrawal successful.");
                        break;
                    case 5:
                        System.out.print("Enter Source Account Number: ");
                        String fromAcc = scanner.nextLine();
                        System.out.print("Enter Destination Account Number: ");
                        String toAcc = scanner.nextLine();
                        System.out.print("Enter Amount to Transfer: ");
                        double transAmt = scanner.nextDouble();
                        bankService.transfer(fromAcc, toAcc, transAmt);
                        System.out.println("Transfer successful.");
                        break;
                    case 6:
                        System.out.print("Enter Account Number: ");
                        String checkAccNum = scanner.nextLine();
                        double bal = bankService.getAccount(checkAccNum).getBalance();
                        System.out.println("Current Balance: $" + bal);
                        break;
                    case 7:
                        System.out.println("Thank you for using our Banking System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
