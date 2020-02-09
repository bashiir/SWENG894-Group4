package com.apptime.auth.service.impl;

import com.apptime.auth.exception.UserExistingException;
import com.apptime.auth.model.Roles;
import com.apptime.auth.model.Users;
import com.apptime.auth.repository.RoleRepository;
import com.apptime.auth.repository.UserRepository;
import com.apptime.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void createUser(Users user) throws UserExistingException {
        Users userInDb = userRepository.findByUsername(user.getUsername());
        if (userInDb != null) {
            throw new UserExistingException();
        }
        save(user);
    }

    @Override
    public void save(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            List<Roles> roleList = roleRepository.findAll();
            for (Roles role : roleList) {
                if ("user".equalsIgnoreCase(role.getRole())) {
                    Set<Roles> set = Collections.singleton(role);
                    user.setRoles(set);
                    break;
                }
            }
        }
        userRepository.save(user);
    }

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
