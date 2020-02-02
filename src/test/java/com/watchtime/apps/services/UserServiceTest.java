package com.watchtime.apps.services;

import com.watchtime.apps.dao.AccountDao;
import com.watchtime.apps.models.Account;
import com.watchtime.apps.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserServiceImpl service;

    @Test
    public void testGetAccount() {
        AccountDao accountDao = mock(AccountDao.class);
        service.setAccountDao(accountDao);

        // mock the login failed
        when(accountDao.getAccount(anyString(), anyString())).thenReturn(null);
        try {
            service.getAccount("abc", "def");
            fail();
        } catch (RuntimeException e) {
            // this is correct
        }


        Account account = new Account(1L);
        when(accountDao.getAccount(anyString(), anyString())).thenReturn(account);
        Account resultAccount = service.getAccount("abc", "def");
        assertNotNull(resultAccount);
        assertEquals(1L, resultAccount.getId());
    }
}
