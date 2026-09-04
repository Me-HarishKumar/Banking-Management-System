package com.bank.model;

import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER_IN, TRANSFER_OUT
    }

    private String transactionId;
    private String accountNumber;
    private double amount;
    private TransactionType type;
    private LocalDateTime timestamp;
    private String description;

    public Transaction(String transactionId, String accountNumber, double amount, TransactionType type, LocalDateTime timestamp, String description) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
        this.description = description;
    }

    public String getTransactionId() { return transactionId; }
    public String getAccountNumber() { return accountNumber; }
    public double getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDescription() { return description; }
}
