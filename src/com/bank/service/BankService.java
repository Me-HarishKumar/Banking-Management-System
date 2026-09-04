package com.bank.service;

import com.bank.exception.InvalidAccountException;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    // Dependency Injection
    public BankService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    public Account getAccount(String accountNumber) {
        Optional<Account> accountOpt = accountRepository.findById(accountNumber);
        if (accountOpt.isEmpty()) {
            throw new InvalidAccountException("Account not found: " + accountNumber);
        }
        return accountOpt.get();
    }

    public void deposit(String accountNumber, double amount, String description) {
        Account account = getAccount(accountNumber);
        account.deposit(amount);
        accountRepository.update(account);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                accountNumber,
                amount,
                Transaction.TransactionType.DEPOSIT,
                LocalDateTime.now(),
                description
        );
        transactionRepository.save(transaction);
    }

    public void withdraw(String accountNumber, double amount, String description) {
        Account account = getAccount(accountNumber);
        account.withdraw(amount);
        accountRepository.update(account);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                accountNumber,
                amount,
                Transaction.TransactionType.WITHDRAWAL,
                LocalDateTime.now(),
                description
        );
        transactionRepository.save(transaction);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);

        Transaction outTransaction = new Transaction(
                UUID.randomUUID().toString(),
                fromAccountNumber,
                amount,
                Transaction.TransactionType.TRANSFER_OUT,
                LocalDateTime.now(),
                "Transfer to " + toAccountNumber
        );
        transactionRepository.save(outTransaction);

        Transaction inTransaction = new Transaction(
                UUID.randomUUID().toString(),
                toAccountNumber,
                amount,
                Transaction.TransactionType.TRANSFER_IN,
                LocalDateTime.now(),
                "Transfer from " + fromAccountNumber
        );
        transactionRepository.save(inTransaction);
    }
}
