package com.watchtime.apps.contollers;

import com.watchtime.apps.models.Account;
import com.watchtime.apps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserContoller {
    @Autowired
    private UserService userService;

    @PostMapping(path="/user", consumes = "application/json", produces = "application/json")
    public Account signup(@RequestBody Account account) {
        boolean result = userService.createAccount(account.getUsername(), account.getPassword(), account.getEmail(), account.getFirstName(), account.getLastName());
        if (result) {
            return account;
        } else {
            return null;
        }
    }

}
