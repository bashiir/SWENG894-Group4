package com.watchtime.apps.dao;

import com.watchtime.apps.models.Account;

public interface AccountDao {
    Account getAccount(String username, String password);
    Account getAccountById(Long id);
    boolean createAccount(String username, String password, String email, String firstName, String lastName);
}
