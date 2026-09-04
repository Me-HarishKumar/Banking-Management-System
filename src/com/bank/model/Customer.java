package com.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String email;
    private List<Account> accounts;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accounts = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Account> getAccounts() { return accounts; }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}
