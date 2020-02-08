package com.apptime.auth.service;

public interface UserService {
    boolean resetPassword(String username, String oldPassword, String newPassword);
}
