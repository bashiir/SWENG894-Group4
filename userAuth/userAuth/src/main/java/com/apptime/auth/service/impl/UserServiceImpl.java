package com.apptime.auth.service.impl;

import com.apptime.auth.model.Users;
import com.apptime.auth.repository.UserRepository;
import com.apptime.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean resetPassword(String username, String oldPassword, String newPassword) {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        } else {
            // wrong password
            return false;
        }
    }
}
