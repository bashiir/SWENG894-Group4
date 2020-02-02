package com.watchtime.apps.services.impl;

import com.watchtime.apps.dao.AccountDao;
import com.watchtime.apps.models.Account;
import com.watchtime.apps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private AccountDao accountDao;

    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account getAccount(String username, String password) {
        Account account = accountDao.getAccount(username, encryptPassword(password));
        if (account == null) {
            // the credential is not valid
            // FIXME we should create a customized exception
            throw new RuntimeException(String.format("Cannot find account by username %s", username));
        }
        return account;
    }

    @Override
    public boolean createAccount(String username, String password, String email, String firstName, String lastName) {
        return false;
    }

    private String encryptPassword(String password) {
        // FIXME we should encrypt password before pass it to DAO
        return password;
    }
}
