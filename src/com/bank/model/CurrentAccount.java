package com.bank.model;

import com.bank.exception.InsufficientBalanceException;
import com.bank.exception.InvalidTransactionException;

public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 500.0;

    public CurrentAccount(String accountNumber, String customerId, double balance) {
        super(accountNumber, customerId, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new InvalidTransactionException("Withdrawal amount must be positive.");
        }
        if (balance + OVERDRAFT_LIMIT < amount) {
            throw new InsufficientBalanceException("Withdrawal exceeds overdraft limit.");
        }
        balance -= amount;
    }

    @Override
    public String getAccountType() {
        return "CURRENT";
    }
}
