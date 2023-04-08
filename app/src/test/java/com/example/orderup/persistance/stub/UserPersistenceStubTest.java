package com.example.orderup.persistance.stub;

import static org.junit.Assert.assertEquals;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;
import com.example.orderup.persistance.UserPersistence;

import org.junit.Test;

public class UserPersistenceStubTest {
    /*

    @Test
    public void addUser() {
        UserServices temp = new UserServices();
        String email = "new@email.com";
        String password = "new123";
        String firstName = "Jane";
        String lastName = "Doe";

        UserPersistence userPersistence;
        userPersistence = temp.getUserPersistence();
        userPersistence.addUser(email,password,firstName,lastName);
        assertEquals(firstName, temp.getFirstName(email));
        assertEquals(lastName, temp.getLastName(email));

    }

    @Test
    public void addCreditCard() {
        UserServices temp = new UserServices();
        String email = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        String cardNum = "5555555555555555";
        String cvc = "505";
        String expiry = "06/22";
        UserPersistence userPersistence;
        userPersistence = temp.getUserPersistence();
        userPersistence.addCreditCard(expiry, email,cardNum, cvc);
        assertEquals(cardNum, temp.getCredit(email));
        assertEquals(cvc, temp.getCvc(email));
        assertEquals(expiry, temp.getExpiry(email));

        userPersistence.addCreditCard(nonexistingEmail,cardNum, cvc,expiry);
        assertEquals(null, temp.getCredit(nonexistingEmail));
        assertEquals(null, temp.getCvc(nonexistingEmail));
        assertEquals(null, temp.getExpiry(nonexistingEmail));


    }

    @Test
    public void updateAddress() {
        UserServices temp = new UserServices();
        String email = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        String newAddress = "123 Main Street";
        UserPersistence userPersistence;
        userPersistence = temp.getUserPersistence();

        userPersistence.updateAddress(email, newAddress);
        assertEquals(newAddress, temp.getAddress(email));

        userPersistence.updateAddress(nonexistingEmail, newAddress);
        assertEquals(null, temp.getAddress(nonexistingEmail));

    }

    @Test
    public void modifyBalance() {
        UserServices temp = new UserServices();
        String email = "admin@email.com";
        String nonexistingEmail = "nothere@email.com";
        float newBalance = 10.00F;
        UserPersistence userPersistence;
        userPersistence = temp.getUserPersistence();

        userPersistence.modifyBalance(email, newBalance);
        assertEquals("" + newBalance, temp.getBalance(email));

        userPersistence.modifyBalance(nonexistingEmail, newBalance);
        assertEquals(null, temp.getBalance(nonexistingEmail));
    }

    @Test
    public void getGiftcards() {
        UserServices temp = new UserServices();
        UserPersistence userPersistence;
        userPersistence = temp.getUserPersistence();
        Giftcard[] test = new Giftcard[0];
        assertEquals(test.length, userPersistence.getGiftcards().length);
    }

     */
}