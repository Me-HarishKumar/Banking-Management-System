package com.bank.repository;

import com.bank.model.Account;
import java.util.Optional;

public interface AccountRepository {
    void save(Account account);
    Optional<Account> findById(String accountNumber);
    void update(Account account);
}
