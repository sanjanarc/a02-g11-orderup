package com.example.orderup.persistance.stub;

import static org.junit.Assert.assertEquals;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;
import com.example.orderup.persistance.UserPersistence;

import org.junit.Test;

public class UserPersistenceStubTest {

    @Test
    public void addUser() {
        String email = "new@email.com";
        String password = "new123";
        String firstName = "Jane";
        String lastName = "Doe";

        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        userPersistence.addUser(email,password,firstName,lastName);
        assertEquals(UserServices.getFirstName(email), firstName);
        assertEquals(UserServices.getLastName(email), lastName);

    }

    @Test
    public void addCreditCard() {
        String email = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        String cardNum = "5555555555555555";
        String cvc = "505";
        String expiry = "06/22";
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        userPersistence.addCreditCard(email,cardNum, cvc,expiry);
        assertEquals(UserServices.getCredit(email), cardNum);
        assertEquals(UserServices.getCvc(email), cvc);
        assertEquals(UserServices.getExpiry(email), expiry);

        userPersistence.addCreditCard(nonexistingEmail,cardNum, cvc,expiry);
        assertEquals(UserServices.getCredit(nonexistingEmail), null);
        assertEquals(UserServices.getCvc(nonexistingEmail), null);
        assertEquals(UserServices.getExpiry(nonexistingEmail), null);


    }

    @Test
    public void updateAddress() {
        String email = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        String newAddress = "123 Main Street";
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();

        userPersistence.updateAddress(email, newAddress);
        assertEquals(UserServices.getAddress(email),newAddress);

        userPersistence.updateAddress(nonexistingEmail, newAddress);
        assertEquals(UserServices.getAddress(nonexistingEmail),null);

    }

    @Test
    public void modifyBalance() {
        String email = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        float newBalance = 10.00F;
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();

        userPersistence.modifyBalance(email, newBalance);
        assertEquals(UserServices.getBalance(email), "" + newBalance);

        userPersistence.modifyBalance(nonexistingEmail, newBalance);
        assertEquals(UserServices.getBalance(nonexistingEmail), null);
    }

    @Test
    public void getGiftcards() {
        UserPersistence userPersistence;
        userPersistence = Services.getUserPersistence();
        Giftcard[] test = new Giftcard[0];
        assertEquals(userPersistence.getGiftcards().length, test.length);
    }
}