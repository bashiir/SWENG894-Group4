package com.apptime.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apptime.auth.model.Users;
import com.apptime.auth.repository.UserRepository;

@Service
public class AuthUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        AuthUserDetails userDetail = null;
        if (user != null) {
            userDetail = new AuthUserDetails();
            userDetail.setUser(user);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetail;
    }

}
