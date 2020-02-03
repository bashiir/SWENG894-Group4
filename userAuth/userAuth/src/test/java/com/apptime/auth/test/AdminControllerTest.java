package com.apptime.auth.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.apptime.auth.controller.AdminController;
import com.apptime.auth.controller.UserController;
import com.apptime.auth.model.Roles;
import com.apptime.auth.model.Users;
import com.apptime.auth.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

 
@ExtendWith(MockitoExtension.class)
public class AdminControllerTest 
{
    @InjectMocks
    UserController employeeController;
     
    @Mock
    UserRepository userRepo;
    @Mock
    MockMvc mockMvc;
    @Mock
    Users user;
    @Mock
    Roles role;
    @Mock
    BCryptPasswordEncoder pEncoder;
     
    @Test
    public void testsignup() throws Exception 
    {
    	//Mockito.when(player.getWeapon()).thenReturn("Sword");
    	Mockito.when(user.getPassword()).thenReturn("password");
    	role.setRole("USER");
    	role.setRole_id(3);
    	 Set<Roles> set = new HashSet<Roles>(); 
    	 set.add(role);

    	Mockito.when(user.getRoles()).thenReturn(set);

    	mockMvc.perform(MockMvcRequestBuilders.post("/signup")
    			  .content(asJsonString(new Users()))
    			  .contentType(MediaType.APPLICATION_JSON)
    			  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());  	
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
    @Test
    public void testSignin() throws Exception 
    {
    	mockMvc.perform(MockMvcRequestBuilders.get("/dashboard")
    	    	  .content("")
    	    	  .contentType(MediaType.APPLICATION_JSON).header("Authorization", "Basic test:test")
    	    	  .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}