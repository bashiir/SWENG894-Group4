package com.apptime.auth.service.impl;

import com.apptime.auth.model.Users;
import com.apptime.auth.repository.UserRepository;
import com.apptime.auth.service.AuthUserDetailService;
import com.apptime.auth.service.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailServiceImpl implements AuthUserDetailService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        AuthUserDetails userDetail;
        if (user != null) {
            userDetail = new AuthUserDetails();
            userDetail.setUser(user);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetail;
    }

}
