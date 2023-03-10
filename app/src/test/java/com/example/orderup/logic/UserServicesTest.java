package com.example.orderup.logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserServicesTest {

    @Test
    public void getFirstName() {
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        assertEquals(UserServices.getFirstName(existingEmail), "admin");
        assertEquals(UserServices.getFirstName(nonexistingEmail), null);
    }

    @Test
    public void getLastName() {
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        assertEquals(UserServices.getLastName(existingEmail), "admin");
        assertEquals(UserServices.getLastName(nonexistingEmail), null);
    }

    @Test
    public void getAddress() {
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        assertEquals(UserServices.getAddress(existingEmail), "admin");
        assertEquals(UserServices.getAddress(nonexistingEmail), null);
    }

    @Test
    public void getBalance() {
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        assertEquals(UserServices.getBalance(existingEmail), "admin");
        assertEquals(UserServices.getBalance(nonexistingEmail), null);
    }
}