package com.watchtime.apps.services;

import com.watchtime.apps.models.Account;

public interface UserService {
    Account getAccount(String username, String password);
    boolean createAccount(String username, String password, String email, String firstName, String lastName);
}
