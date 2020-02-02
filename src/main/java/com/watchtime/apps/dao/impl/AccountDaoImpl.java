package com.watchtime.apps.dao.impl;

import com.watchtime.apps.dao.AccountDao;
import com.watchtime.apps.models.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {
    @Override
    public Account getAccount(String username, String password) {
        return null;
    }

    @Override
    public Account getAccountById(Long id) {
        return null;
    }

    @Override
    public boolean createAccount(String username, String password, String email, String firstName, String lastName) {
        return false;
    }
}
