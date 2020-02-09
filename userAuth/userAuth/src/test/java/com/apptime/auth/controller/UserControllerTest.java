package com.apptime.auth.controller;

import com.apptime.auth.model.Roles;
import com.apptime.auth.model.Users;
import com.apptime.auth.repository.UserRepository;
import com.apptime.auth.service.AuthUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Autowired
    UserRepository userRepo;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BCryptPasswordEncoder pEncoder;

    @Mock
    AuthUserDetailService authUserDetailService;

    @BeforeEach
    public void init() {
        cleanup();
    }

    @AfterEach
    public void cleanup() {
        userRepo.deleteAll();
    }

    @Test
    public void testSignup() throws Exception {
        Users user = new Users();
        String username = UUID.randomUUID().toString();
        user.setUsername(username);
        user.setPassword("password");
        user.setEmail("user@test.com");
        Roles role = new Roles();
        role.setRole("USER");
        role.setRole_id(3);
        Set<Roles> set = new HashSet<>();
        set.add(role);
        user.setRoles(set);

        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        // try same username again
        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isConflict());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    public void testDashboard() throws Exception {
        Users user = new Users();
        user.setUsername("user1");
        user.setPassword("password1");
        user.setEmail("user1@test.com");
        userRepo.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/dashboard")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());

        user.setUsername("user");
        user.setPassword("password");
        user.setEmail("user@test.com");
        userRepo.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/dashboard")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testLogin() throws Exception {
        final String username = "username";
        final String password = "password";
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(pEncoder.encode(password));
        user.setEmail("user@test.com");
        userRepo.save(user);

        Users loginUser = new Users();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginUser))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        // test login with wrong password
        loginUser.setPassword(UUID.randomUUID().toString());
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginUser))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());

        // test login with wrong username
        loginUser.setUsername(UUID.randomUUID().toString());
        loginUser.setPassword(password);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(loginUser))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }

}