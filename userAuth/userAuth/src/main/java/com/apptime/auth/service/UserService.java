package com.apptime.auth.service;

import com.apptime.auth.exception.UserExistingException;
import com.apptime.auth.model.Users;

public interface UserService {
    Users findByUsername(String username);
    void createUser(Users user) throws UserExistingException;
    void save(Users user);
    boolean resetPassword(String username, String oldPassword, String newPassword);
}
