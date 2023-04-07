package com.example.orderup.logic;

import android.util.Log;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.util.IllegalFormatException;
import java.util.IllegalFormatFlagsException;
import java.util.NoSuchElementException;

import javax.security.auth.login.LoginException;

/**
 * This class verifies Information provided by User: name,email and password, address, credit card and gift card format before creating an Account for the User.
 */
public class UserVerification {

    // Store the database instances
    private final UserPersistence userPersistence;

    /**
     * Constructor
     *
     * @param userPersistence the database going to use.
     */
    public UserVerification(UserPersistence userPersistence) {
        this.userPersistence = userPersistence; //Dependency injection the database.
    }

    /**
     * Verify the input email and password with databases.
     *
     * @param email    The input email.
     * @param password The input password.
     * @throws Exception Will throw error if the input data is not correct.
     */
    public void loginVerification(String email, String password) throws Exception, MyException {

        if (email.equals("") || password.equals("")) { // Input cannot be empty.

            throw new MyException.EXCEPTION_EMPTY_INPUT();

        } else if (!emailCheck(email)) { // Email format must meet standard format.

            throw new MyException.EXCEPTION_ILLEGAL_FORMAT();

        } else { // Compare the entered password with the account password.

            // Search the input email from database.
            User tempUser = userPersistence.getUserTable().get(email);

            if (tempUser != null) { // Match the password iff the email exists in the database.

                if (!tempUser.getPassword().equals(password)) {

                    throw new MyException.EXCEPTION_INVALID_PASSWORD();

                }

            } else {

                throw new MyException.EXCEPTION_ITEM_DOES_NOT_EXIST();

            }
        }
    }

    /**
     * Verify the inputs and create account if input correct.
     *
     * @param email      The email of the user.
     * @param firstName  the first name of the user.
     * @param lastName   the last name of the user.
     * @param password   the password for login.
     * @param rePassword re-enter the password for double confirm.
     */
    public void registrationVerification(String email, String firstName, String lastName, String password,
                                         String rePassword) throws Exception {

        if (firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("")
                || rePassword.equals("")) { // Inputs cannot be empty.

            throw new MyException.EXCEPTION_EMPTY_INPUT();

        } else if (!emailCheck(email)) { // Email format must meet standard format.

            throw new MyException.EXCEPTION_ILLEGAL_FORMAT();

        } else if (!password.equals(rePassword)) { // Password must be the same.

            throw new MyException.EXCEPTION_INVALID_PASSWORD();

        } else if (password.length() < 6) { // Password must more than 6 character.

            throw new MyException.EXCEPTION_PASSWORD_NOT_ENOUGH_LENGTH();

        } else if (firstName.length() > 7) { // First name cannot more than 7 character.

            throw new MyException.EXCEPTION_INPUT_OVER_FLOW();

        } else if (lastName.length() > 7) { // Last name cannot more than 7 character.

            throw new MyException.EXCEPTION_INPUT_OVER_FLOW2();

        } else {
            // Search the input email from database.
            User tempUser = userPersistence.getUserTable().get(email);

            if (tempUser != null) { // Registered email is already exist in the database and cannot be used again.

                throw new MyException.EXCEPTION_ITEM_ALREADY_EXIST();

            } else {

                userPersistence.addUser(email, password, firstName, lastName); // Create a user to stub or database.
                userPersistence.modifyBalance(email, 0.00F); // Set the user balance to 0.
            }
        }
    }

    /**
     * Verify the input credit card format.
     *
     * @param email      the user email.
     * @param cardNum    the user credit card number.
     * @param cardCvc    the user credit card cvc.
     * @param cardExpiry the user credit card expiry date.
     */
    public void creditCardVerification(String email, String cardNum, String cardCvc, String cardExpiry) {

        if (!(cardNum.equals("") || cardCvc.equals("") || cardExpiry.equals(""))) { // Inputs cannot be empty.

            if (cardNum.length() != 16) {
                msg = "Error: Incorrect Card Number Format.";
            } else if (cardNum.charAt(0) != '2' && cardNum.charAt(0) != '3'
                    && cardNum.charAt(0) != '4' && cardNum.charAt(0) != '5') {
                msg = "Error: Card is not Visa, American Express or Mastercard.";
            } else if (cardCvc.length() != 3 && cardCvc.length() != 4) {
                msg = "Error: Incorrect CVC length.";
            } else if (cardExpiry.length() != 5) {
                msg = "Error: Incorrect Expiry date length.";
            } else if (cardExpiry.charAt(2) != '/' || (cardExpiry.charAt(0) != '0' && cardExpiry.charAt(0) != '1')
                    || (cardExpiry.charAt(0) == '1' && Character.getNumericValue(cardExpiry.charAt(1)) >= 3)) {
                msg = "Error: Incorrect Expiry date.";
            } else {
                // Add the credit card to the database.
                userPersistence.addCreditCard(email, cardNum, cardCvc, cardExpiry);
                msg = "Credit Card added.";
            }
        } else {
            msg = "Missing Field: Please check you have entered all fields.";
        }
    }

    // Verity the input address format and return the message to user.
    public void addressVerification(String street, String city, String province, String postal, String email,
                                             String address) {
        String result = streetVerification(street) + cityVerification(city) + provinceVerification(province)
                + postalVerification(postal);
        if (result.equals("")) {
            // No error occur than add the address to database.
            userPersistence.updateAddress(email, address);
            return "Address added.";
        } else {
            return result;
        }
    }

    // Check the street section of the address.
    private static String streetVerification(String street) {
        if (street == null || street.equals("")) {
            return "Error: Address format incorrect.\n";
        } else {
            return "";
        }
    }

    // Check the city is Winnipeg or not.
    private static String cityVerification(String city) {
        if (!city.equalsIgnoreCase("Winnipeg")) {
            return "Error: The city you entered must be located within Manitoba.\n";
        } else {
            return "";
        }
    }

    // Check the province is Manitoba or other province.
    private static String provinceVerification(String province) {
        if (!province.equalsIgnoreCase("Manitoba")) {
            return "Error: Currently does not support other province other than Manitoba.\n";
        } else {
            return "";
        }
    }

    // Check the format of the postal code.
    private static String postalVerification(String postal) {
        String msg;

        if (postal.length() != 6) {
            msg = "Error: Invalid postal code length.\n";
        } else if (Character.toUpperCase(postal.charAt(0)) != 'R') {
            msg = "Error: Postal Code not located in Manitoba.\n";
        } else if (!Character.isLetter(postal.charAt(0)) || !Character.isDigit(postal.charAt(1))
                || !Character.isLetter(postal.charAt(2)) || !Character.isDigit(postal.charAt(3))
                || !Character.isLetter(postal.charAt(4)) || !Character.isDigit(postal.charAt(5))) {
            msg = "Error: Invalid postal code format.\n";
        } else {
            msg = "";
        }

        return msg;
    }

    public static String giftCardVerification(String email, String card) {
        Giftcard[] cardList = userPersistence.getGiftcards();
        Float amount = 0.00F;
        String msg = "";
        boolean found = false;

        for (int i = 0; i < 5; i++) {
            if (card.equals(cardList[i].getNumber())) {
                Log.d("stored", cardList[i].getNumber());
                Log.d("entered", card);
                amount = cardList[i].getAmount();
                found = true;
            }
        }

        if (card.length() != 16) {
            msg = "Error: Invalid gift card format, must be 16 digits.";
        } else if (!found) {
            msg = "Error: Gift card not found in our system.";
        } else if (found) {
            msg = "";
            userPersistence.modifyBalance(email, amount);
        }

        return msg;
    }

    // Make sure the email input contain character "@".
    public static boolean emailCheck(String email) {
        boolean flag = false;
        boolean checkPeriod = false;
        boolean multiplesAts = false;
        int counter = 1;
        char at = '@';

        while (email.length() - 1 > counter) {
            if (email.charAt(counter) == at && !flag) {
                flag = true;
            } else if (email.charAt(counter) == at && flag) {
                multiplesAts = true;
            }

            if (flag && email.charAt(counter) == '.' && counter < email.length() - 1) {
                checkPeriod = true;
            }

            counter++;
        }
        return flag && checkPeriod && !multiplesAts;
    }
}