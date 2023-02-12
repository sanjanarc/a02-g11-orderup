package com.example.orderup.Objects;

import junit.framework.TestCase;

import org.junit.Test;

public class UserTest extends TestCase {

    @Test
    public void testGetFirstName() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(firstName,user.getFirstName());
    }
    @Test
    public void testGetLastName() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(lastName,user.getLastName());
    }
    @Test
    public void testGetEmail() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(email,user.getEmail());
    }
    @Test
    public void testGetPassword() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(password,user.getPassword());
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
        assertEquals(address,user.getAddress());

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

        assertEquals(creditCard,user.getCreditCard());

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

        assertEquals(cvc,user.getCvc());

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

        assertEquals(expiry,user.getExpiry());

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
        assertEquals(firstName2,user.getFirstName());
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
        assertEquals(lastName2,user.getLastName());
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
        assertEquals(password2,user.getPassword());
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
        assertEquals(address,user.getAddress());

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

        assertEquals(creditCard,user.getCreditCard());
        assertEquals(cvc,user.getCvc());
        assertEquals(expiry,user.getExpiry());

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
}