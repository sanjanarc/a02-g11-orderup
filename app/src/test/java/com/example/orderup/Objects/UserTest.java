package com.example.orderup.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

    @Test
    public void testUserConstructor() {
        String email = "test@example.com";
        String password = "password123";
        String firstName = "John";
        String lastName = "Doe";
        String creditCard = "1234-5678-9012-3456";
        String cvc = "123";
        String expiry = "12/23";
        String address = "123 Main St";
        String balance = "50.00F";

        User user = new User(email, password, firstName, lastName, creditCard, cvc, expiry, address, balance, Boolean.TRUE);

        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(creditCard, user.getCreditCard());
        assertEquals(cvc, user.getCvc());
        assertEquals(expiry, user.getExpiry());
        assertEquals(address, user.getAddress());
        assertEquals(50.0, user.getBalance(), 0.001);
    }


    @Test
    public void testGetFirstName() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(firstName, user.getFirstName());
    }
    @Test
    public void testGetLastName() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(lastName, user.getLastName());
    }
    @Test
    public void testGetEmail() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(email, user.getEmail());
    }
    @Test
    public void testGetPassword() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(password, user.getPassword());
    }
    @Test
    public void testGetAddress() {
        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";
        String address = "123 Main Street";
        User user = new User(firstName, lastName, email, password);
        user.updateAddress(address);
        assertEquals(address, user.getAddress());

    }

    @Test
    public void testGetCreditCard() {
        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        String creditCard = "4326107452562046";
        String cvc = "4703";
        String expiry = "03/23";

        User user = new User(firstName, lastName, email, password);
        user.addCreditCard(creditCard,cvc,expiry);

        assertEquals(creditCard, user.getCreditCard());

    }
    @Test
    public void testGetCvc() {
        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        String creditCard = "4326107452562046";
        String cvc = "4703";
        String expiry = "03/23";

        User user = new User(firstName, lastName, email, password);
        user.addCreditCard(creditCard,cvc,expiry);

        assertEquals(cvc, user.getCvc());

    }
    @Test
    public void testGetExpiry() {
        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        String creditCard = "4326107452562046";
        String cvc = "4703";
        String expiry = "03/23";

        User user = new User(firstName, lastName, email, password);
        user.addCreditCard(creditCard,cvc,expiry);

        assertEquals(expiry, user.getExpiry());

    }
    @Test
    public void testUpdateFirstName() {

        String firstName= "John";
        String firstName2= "Jane";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";
        User user = new User(firstName, lastName, email, password);

        user.updateFirstName(firstName2);
        assertEquals(firstName2, user.getFirstName());
    }
    @Test
    public void testUpdateLastName() {

        String firstName= "John";
        String lastName= "Doe";
        String lastName2= "Smith";
        String email = "johndoe@gmail.com";
        String password = "administrator";
        User user = new User(firstName, lastName, email, password);

        user.updateLastName(lastName2);
        assertEquals(lastName2, user.getLastName());
    }
    @Test
    public void testUpdatePass() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";
        String password2= "test123";
        User user = new User(firstName, lastName, email, password);

        user.updatePass(password2);
        assertEquals(password2, user.getPassword());
    }
    @Test
    public void testUpdateAddress() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";
        String address = "123 Main Street";
        User user = new User(firstName, lastName, email, password);

        user.updateAddress(address);
        assertEquals(address, user.getAddress());

    }
    @Test
    public void testAddCreditCard() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";
        String creditCard = "4326107452562046";
        String cvc = "4703";
        String expiry = "03/23";

        User user = new User(firstName, lastName, email, password);
        user.addCreditCard(creditCard,cvc,expiry);

        assertEquals(creditCard, user.getCreditCard());
        assertEquals(cvc, user.getCvc());
        assertEquals(expiry, user.getExpiry());

    }
    @Test
    public void testTestEquals() {
        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        String firstName2= "Jane";
        String lastName2= "Smith";
        String email2 = "janesmith@gmail.com";
        String password2 = "administrator";

        User john = new User(firstName, lastName, email, password);
        User john2 = new User(firstName, lastName, email, password);
        User jane = new User(firstName2, lastName2, email2, password2);

        assertEquals(true, john.equals(john2));
        assertEquals(false, john.equals(jane));

    }

    @Test
    public void getMembership() {
        User user = new User("test@gmail.com", "password", "John", "Doe", "1234-5678-9012-3456", "123", "01/24", "123 Main St", "10.00F", true);
        assertTrue(user.getMembership());

        user = new User("test2@gmail.com", "password", "Jane", "Doe", "1234-5678-9012-3456", "123", "01/24", "456 Main St", "5.00F", false);
        assertFalse(user.getMembership());

    }
}