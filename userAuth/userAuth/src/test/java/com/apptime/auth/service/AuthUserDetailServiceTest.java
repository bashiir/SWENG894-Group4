package com.apptime.auth.service;

import com.apptime.auth.model.Users;
import com.apptime.auth.repository.UserRepository;
import com.apptime.auth.service.impl.AuthUserDetailServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AuthUserDetailServiceTest {
    @Autowired
    AuthUserDetailServiceImpl service;

    @Autowired
    UserRepository userRepo;

    private static final String username = "username";
    private static final String password = "password";
    private static final String email = "email@test.com";

    @BeforeEach
    public void init() {
        cleanup();

        final Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userRepo.save(user);
    }

    @AfterEach
    public void cleanup() {
        userRepo.deleteAll();
    }

    @Test
    public void testLoadUserByUsername() {
        UserDetails userDetails = service.loadUserByUsername(username);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());

        // try to find a user with non-existing username
        try {
            service.loadUserByUsername(UUID.randomUUID().toString());
            fail();
        } catch (UsernameNotFoundException e) {
            // correct
        } catch (Exception e) {
            // if there is any exception except UsernameNotFoundException
            fail();
        }
    }
}
