package com.watchtime.apps.services.impl;

import com.watchtime.apps.services.ContactService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Override
    public boolean addContact(Long userId, Long contactUserId) {
        if (userId == null || contactUserId == null || userId.equals(contactUserId)) {
            return false;
        }
        return true;
    }

    @Override
    public List<Long> getContacts(Long userId) {
        if (userId == null) {
            return null;
        }
        return new ArrayList<>();
    }
}
