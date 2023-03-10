package com.example.orderup.logic;

import static org.junit.Assert.*;

import com.example.orderup.persistance.UserPersistence;

import org.junit.Test;

public class UserVerificationTest {

    @Test
    public void loginVerificationTest() {
        assertEquals("Email or Password is Empty.", UserVerification.loginVerification("", ""));
        assertEquals("Incorrect Email Format.", UserVerification.loginVerification("12@3@1@23.com", "2123456"));
        assertEquals("Incorrect Email Format.", UserVerification.loginVerification("123@123dotcom", "879789"));
        assertEquals("Email does not exist.", UserVerification.loginVerification("email@not.exist", "correctFormat"));
        assertEquals("Incorrect Password.", UserVerification.loginVerification("admin@email.com", "wrongPassword"));
        assertEquals(null, UserVerification.loginVerification("admin@email.com", "admin"));
    }

    @Test
    public void registrationVerificationTest() {
        assertEquals("Missing Field: Please check you have entered all fields.", UserVerification.registrationVerification("", "", "", "", ""));
        assertEquals("Incorrect Email Format.", UserVerification.registrationVerification("123@123@123.com", "123123", "123123", "123123", "123123"));
        assertEquals("Incorrect Email Format.", UserVerification.registrationVerification("123@123dotcom", "123123", "123123", "123123", "123123"));
        assertEquals("Password and Re-password do not match.", UserVerification.registrationVerification("imcorrect@email.com", "correct", "email", "noPasword", "YesPassword"));
        assertEquals("Password needs to be at least 6 characters long.", UserVerification.registrationVerification("imcorrect@email.com", "correct", "email", "only5", "only5"));
        assertEquals("Your first name is more than 7 character.", UserVerification.registrationVerification("imcorrect@email.com", "moreThan7?", "only5", "correctPass", "correctPass"));
        assertEquals("Your last name is more than 7 character.", UserVerification.registrationVerification("imcorrect@email.com", "only5", "moreThan7", "correctPass", "correctPass"));
        assertEquals("Email already in use.", UserVerification.registrationVerification("admin@email.com", "noSame", "noSame", "donotSame", "donotSame"));
        assertEquals(null, UserVerification.registrationVerification("newAcc@email.com", "newAcc", "ohYeah", "niceOne", "niceOne"));
    }

    @Test
    public void creditCardVerificationTest() {
        assertEquals("Missing Field: Please check you have entered all fields.", UserVerification.creditCardVerification("", "", "", ""));
        assertEquals("Error: Incorrect Card Number Format.", UserVerification.creditCardVerification("donotcare", "noteven16digit", "donotcare", "donotcare"));
        assertEquals("Error: Card is not Visa, American Express or Mastercard.", UserVerification.creditCardVerification("donotcare", "1234567891234567", "donotcare", "donotcare"));
        assertEquals("Error: Card is not Visa, American Express or Mastercard.", UserVerification.creditCardVerification("donotcare", "6234567891234567", "donotcare", "donotcare"));
        assertEquals("Error: Card is not Visa, American Express or Mastercard.", UserVerification.creditCardVerification("donotcare", "7234567891234567", "donotcare", "donotcare"));
        assertEquals("Error: Card is not Visa, American Express or Mastercard.", UserVerification.creditCardVerification("donotcare", "8234567891234567", "donotcare", "donotcare"));
        assertEquals("Error: Incorrect CVC length.", UserVerification.creditCardVerification("donotcare", "2234567891234567", "00", "donotcare"));
        assertEquals("Error: Incorrect CVC length.", UserVerification.creditCardVerification("donotcare", "2234567891234567", "00000", "donotcare"));
        assertEquals("Error: Incorrect Expiry date length.", UserVerification.creditCardVerification("", "", "", ""));
        assertEquals("Missing Field: Please check you have entered all fields.", UserVerification.creditCardVerification("", "", "", ""));
        assertEquals("Missing Field: Please check you have entered all fields.", UserVerification.creditCardVerification("", "", "", ""));
        assertEquals("Missing Field: Please check you have entered all fields.", UserVerification.creditCardVerification("", "", "", ""));
        assertEquals("Missing Field: Please check you have entered all fields.", UserVerification.creditCardVerification("", "", "", ""));
    }

    @Test
    public void addressVerificationTest() {
    }

    @Test
    public void giftCardVerificationTest() {
    }

    @Test
    public void emailCheckTest() {
    }
}