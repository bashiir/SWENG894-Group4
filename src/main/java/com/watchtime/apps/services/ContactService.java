package com.watchtime.apps.services;

import java.util.List;

public interface ContactService {
    // Sample methods
    boolean addContact(Long userId, Long contactUserId);
    List<Long> getContacts(Long userId);
}
