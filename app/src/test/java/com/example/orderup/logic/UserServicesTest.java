package com.example.orderup.logic;

import static org.junit.Assert.assertEquals;

import com.example.orderup.persistance.UserPersistence;

import org.junit.Test;

public class UserServicesTest {

    @Test
    public void getFirstName() {
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        assertEquals(UserServices.getFirstName(existingEmail), "admin@email.com");
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
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        String newAddress = "123 Main Street";

        userPersistence.updateAddress(existingEmail,newAddress);
        userPersistence.updateAddress(nonexistingEmail,newAddress);
        assertEquals(UserServices.getAddress(existingEmail), "123 Main Street");
        assertEquals(UserServices.getAddress(nonexistingEmail), null);
    }

    @Test
    public void getBalance() {
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        String existingEmail = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        float newBalance = 10.00F;
        userPersistence.modifyBalance(existingEmail, newBalance);
        userPersistence.modifyBalance(nonexistingEmail, newBalance);
        assertEquals(UserServices.getBalance(existingEmail), "10.0");
        assertEquals(UserServices.getBalance(nonexistingEmail), null);
    }
}