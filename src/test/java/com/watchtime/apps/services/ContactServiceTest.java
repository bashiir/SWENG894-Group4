package com.watchtime.apps.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class ContactServiceTest {
    @Autowired
    private ContactService contactService;

    @Test
    public void testAddContact() {
        assertFalse(contactService.addContact(null, null));
        assertFalse(contactService.addContact(null, 1L));
        assertFalse(contactService.addContact(1L, null));
        assertFalse(contactService.addContact(1L, 1L));
        assertTrue(contactService.addContact(1L, 2L));
    }

    @Test
    public void testGetContacts() {
        assertNull(contactService.getContacts(null));
        assertNotNull(contactService.getContacts(1L));
    }
}
