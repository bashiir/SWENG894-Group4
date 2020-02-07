package com.apptime.auth.service;

public interface UserService {
    boolean resetPassword(String userId, String originalPassword, String newPassword);
}
