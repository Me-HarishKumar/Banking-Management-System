package com.bank.model;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.04;

    public SavingsAccount(String accountNumber, String customerId, double balance) {
        super(accountNumber, customerId, balance);
    }

    public void applyInterest() {
        double interest = this.balance * INTEREST_RATE;
        this.deposit(interest);
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }
}
