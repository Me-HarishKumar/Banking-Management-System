package com.bank.service;

import com.bank.exception.InsufficientBalanceException;
import com.bank.model.Account;
import com.bank.model.SavingsAccount;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

// Mock implementations for testing
class MockAccountRepository implements AccountRepository {
    private Map<String, Account> db = new HashMap<>();

    @Override
    public void save(Account account) {
        db.put(account.getAccountNumber(), account);
    }

    @Override
    public Optional<Account> findById(String accountNumber) {
        return Optional.ofNullable(db.get(accountNumber));
    }

    @Override
    public void update(Account account) {
        db.put(account.getAccountNumber(), account);
    }
}

class MockTransactionRepository implements TransactionRepository {
    private List<Transaction> db = new ArrayList<>();

    @Override
    public void save(Transaction transaction) {
        db.add(transaction);
    }

    @Override
    public List<Transaction> findByAccountNumber(String accountNumber) {
        return db;
    }
}

public class BankServiceTest {

    private BankService bankService;
    private MockAccountRepository accountRepo;
    private MockTransactionRepository transactionRepo;

    @BeforeEach
    public void setup() {
        accountRepo = new MockAccountRepository();
        transactionRepo = new MockTransactionRepository();
        bankService = new BankService(accountRepo, transactionRepo);
    }

    @Test
    public void testCreateAccount() {
        Account account = new SavingsAccount("101", "C1", 500.0);
        bankService.createAccount(account);

        Account saved = bankService.getAccount("101");
        assertNotNull(saved);
        assertEquals(500.0, saved.getBalance());
    }

    @Test
    public void testDeposit() {
        bankService.createAccount(new SavingsAccount("101", "C1", 500.0));
        bankService.deposit("101", 200.0, "Salary");

        assertEquals(700.0, bankService.getAccount("101").getBalance());
    }

    @Test
    public void testWithdrawSuccess() {
        bankService.createAccount(new SavingsAccount("101", "C1", 500.0));
        bankService.withdraw("101", 100.0, "ATM");

        assertEquals(400.0, bankService.getAccount("101").getBalance());
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        bankService.createAccount(new SavingsAccount("101", "C1", 500.0));

        assertThrows(InsufficientBalanceException.class, () -> {
            bankService.withdraw("101", 600.0, "ATM");
        });
    }

    @Test
    public void testTransferSuccess() {
        bankService.createAccount(new SavingsAccount("101", "C1", 500.0));
        bankService.createAccount(new SavingsAccount("102", "C2", 200.0));

        bankService.transfer("101", "102", 150.0);

        assertEquals(350.0, bankService.getAccount("101").getBalance());
        assertEquals(350.0, bankService.getAccount("102").getBalance());
    }
}
