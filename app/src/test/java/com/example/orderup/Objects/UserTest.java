package com.example.orderup.Objects;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    public void testGetFirstName() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(firstName,user.getFirstName());
    }

    public void testGetLastName() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(lastName,user.getLastName());
    }

    public void testGetEmail() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(email,user.getEmail());
    }

    public void testGetPassword() {

        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";

        User user = new User(firstName, lastName, email, password);
        assertEquals(password,user.getPassword());
    }

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

    public void testTestToString() {
        String firstName= "John";
        String lastName= "Doe";
        String email = "johndoe@gmail.com";
        String password = "administrator";
        String address = "123 Main Street";

        User user = new User(firstName, lastName, email, password);

        String result = String.format("First name: %s\n" + "Last name: %s\n" + "Email: %s\n" + "Password: %s\n" + "Address: %s", firstName, lastName, email, password, address);
        assertEquals(result,user.toString());
    }

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