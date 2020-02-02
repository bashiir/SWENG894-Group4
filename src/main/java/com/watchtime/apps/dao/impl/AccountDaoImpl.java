package com.watchtime.apps.dao.impl;

import com.watchtime.apps.dao.AccountDao;
import com.watchtime.apps.models.Account;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account getAccount(String username, String password) {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Account.class);
        List<Account> list = criteria.list();
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Account getAccountById(Long id) {
        return null;
    }

    @Override
    public boolean createAccount(String username, String password, String email, String firstName, String lastName) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        try {
            entityManager.unwrap(Session.class).save(account);
        } catch (Exception e) {
            // FIXME we should use log4j
            System.err.println("Cannot create account");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
