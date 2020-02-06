package com.apptime.auth.service;

public interface UserService {
    boolean resetPassword(int userId, String originalPassword, String newPassword);
}
