package com.example.orderup.logic;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.util.List;

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
    public void loginVerification(String email, String password) throws Exception {

        if (email.equals("") || password.equals("")) { // Input cannot be empty.

            throw new MyException.EXCEPTION_EMPTY_INPUT();

        } else if (!emailCheck(email)) { // Email format must meet standard format.

            throw new MyException.EXCEPTION_ILLEGAL_FORMAT();

        } else { // Compare the entered password with the account password.

            // Search the input email from database.
            User tempUser = userPersistence.getUser(email);

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
     * @throws Exception will throws exception when input data is incorrect.
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
            User tempUser = userPersistence.getUser(email);

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
     * @throws Exception will throw exception when the input data is incorrect.
     */
    public void creditCardVerification(String email, String cardNum, String cardCvc, String cardExpiry) throws Exception {

        if (!(cardNum.equals("") || cardCvc.equals("") || cardExpiry.equals(""))) { // Inputs cannot be empty.

            if (cardNum.length() != 16) { // Credit card number must equal to 16.

                throw new MyException.EXCEPTION_ILLEGAL_FORMAT();

            } else if (cardNum.charAt(0) != '2' && cardNum.charAt(0) != '3'
                    && cardNum.charAt(0) != '4' && cardNum.charAt(0) != '5') { // Credit card type does not match.

                throw new MyException.EXCEPTION_TYPE_MISMATCH();

            } else if (cardCvc.length() != 3 && cardCvc.length() != 4) { // CVC length must between 3 or 4.

                throw new MyException.EXCEPTION_CVC_LENGTH_DOES_NOT_MATCH();

            } else if (cardExpiry.length() != 5) { // Expiry data length.

                throw new MyException.EXCEPTION_ILLEGAL_DATE_FORMAT();

            } else if (cardExpiry.charAt(2) != '/' || (cardExpiry.charAt(0) != '0' && cardExpiry.charAt(0) != '1')
                    || (cardExpiry.charAt(0) == '1' && Character.getNumericValue(cardExpiry.charAt(1)) >= 3)) { // Expiry date format.

                throw new MyException.EXCEPTION_ILLEGAL_DATE_FORMAT2();

            } else { // Add the credit card to the database.

                userPersistence.addCreditCard(email, cardNum, cardCvc, cardExpiry);

            }

        } else {

            throw new MyException.EXCEPTION_EMPTY_INPUT();

        }
    }


    /**
     * Verify the input credit card format.
     *
     * @param fullName      the user's full name on card.
     * @param cardNum    the user credit card number.
     * @param cardCvc    the user credit card cvc.
     * @param cardExpiry the user credit card expiry date.
     * @throws Exception will throw exception when the input data is incorrect.
     */
    public void paymentVerification(String fullName, String cardNum, String cardCvc, String cardExpiry) throws Exception {

        if (!(cardNum.equals("") || cardCvc.equals("") || cardExpiry.equals("") || fullName.equals(""))) { // Inputs cannot be empty.

            if (cardNum.length() != 16) { // Credit card number must equal to 16.

                throw new MyException.EXCEPTION_ILLEGAL_FORMAT();

            } else if (cardNum.charAt(0) != '2' && cardNum.charAt(0) != '3'
                    && cardNum.charAt(0) != '4' && cardNum.charAt(0) != '5') { // Credit card type does not match.

                throw new MyException.EXCEPTION_TYPE_MISMATCH();

            } else if (cardCvc.length() != 3 && cardCvc.length() != 4) { // CVC length must between 3 or 4.

                throw new MyException.EXCEPTION_CVC_LENGTH_DOES_NOT_MATCH();

            } else if (cardExpiry.length() != 5) { // Expiry data length.

                throw new MyException.EXCEPTION_ILLEGAL_DATE_FORMAT();

            } else if (cardExpiry.charAt(2) != '/' || (cardExpiry.charAt(0) != '0' && cardExpiry.charAt(0) != '1')
                    || (cardExpiry.charAt(0) == '1' && Character.getNumericValue(cardExpiry.charAt(1)) >= 3)) { // Expiry date format.

                throw new MyException.EXCEPTION_ILLEGAL_DATE_FORMAT2();

            } else if (fullName.split(" ").length < 2) { // Full name on card should contain at least two words separated by a space.

                throw new MyException.EXCEPTION_ILLEGAL_FULL_NAME_FORMAT();

            }

        } else {

            throw new MyException.EXCEPTION_EMPTY_INPUT();

        }
    }



    /**
     * Verity the input address format and return the message to user.
     *
     * @param street
     * @param city
     * @param province
     * @param postal
     * @param email
     * @param address
     * @throws Exception will throw exception when input data is incorrect.
     */
    public void addressVerification(String street, String city, String province, String postal, String email,
                                    String address) throws Exception {

        //Verify every input.
        streetVerification(street);
        cityVerification(city);
        provinceVerification(province);
        postalVerification(postal);

        // No error occur than add the address to database.
        userPersistence.updateAddress(email, address);
    }

    /**
     * Check the street section of the address.
     *
     * @param street
     * @throws Exception will throw error format exception.
     */
    private void streetVerification(String street) throws Exception {
        if (street == null || street.equals("")) {

            throw new MyException.EXCEPTION_ILLEGAL_FORMAT();

        }
    }

    /**
     * Check the city is Winnipeg or not.
     *
     * @param city
     * @throws Exception will throw error location exception.
     */
    private void cityVerification(String city) throws Exception {
        if (!city.equalsIgnoreCase("Winnipeg")) {

            throw new MyException.EXCEPTION_LOCATION_OUT_OF_BOUND();

        }
    }

    /**
     * Check the province is Manitoba or other province.
     *
     * @param province
     * @throws Exception will throw error location exception.
     */
    private void provinceVerification(String province) throws Exception {
        if (!province.equalsIgnoreCase("Manitoba")) {

            throw new MyException.EXCEPTION_LOCATION_OUT_OF_BOUND2();

        }
    }

    /**
     * Check the format of the postal code.
     *
     * @param postal
     * @throws Exception will throw error exception related to postal code.
     */
    private void postalVerification(String postal) throws Exception {

        if (postal.length() != 6) { // Length must equal to 6.

            throw new MyException.EXCEPTION_INVALID_POSTAL_CODE_LENGTH();

        } else if (Character.toUpperCase(postal.charAt(0)) != 'R') { // Location invalid.

            throw new MyException.EXCEPTION_LOCATION_OUT_OF_BOUND3();

        } else if (!Character.isLetter(postal.charAt(0)) || !Character.isDigit(postal.charAt(1))
                || !Character.isLetter(postal.charAt(2)) || !Character.isDigit(postal.charAt(3))
                || !Character.isLetter(postal.charAt(4)) || !Character.isDigit(postal.charAt(5))) { // Invalid format.

            throw new MyException.EXCEPTION_INVALID_POSTAL_CODE_FORMAT();
        }
    }

    /**
     * Verify the gift card and add amount to account balance.
     *
     * @param email the user email.
     * @param card  the gift card number.
     * @throws Exception will throw exception when gift card is incorrect.
     */
    public void giftCardVerification(String email, String card) throws Exception {
        List<Giftcard> cardList = userPersistence.getGiftCards();
        Float amount = 0.00F;
        boolean found = false;

        for (int i = 0; i < cardList.size(); i++) {
            if (card.equals(cardList.get(i).getNumber())) {
                amount = cardList.get(i).getAmount();
                found = true;
            }
        }

        if (card.length() != 16) {

            throw new MyException.EXCEPTION_ILLEGAL_FORMAT();

        } else if (!found) {

            throw new MyException.EXCEPTION_ITEM_DOES_NOT_EXIST();

        } else if (found) {

            // Add the amount to the user account.
            userPersistence.modifyBalance(email, amount);

        }
    }

    /**
     * Make sure the email input contain character "@".
     *
     * @param email the user email.
     * @return boolean True if the email format is correct, False if not correct.
     */
    private boolean emailCheck(String email) {
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

    public String verifyMembershipPurchase(String email) throws Exception {

        User user = userPersistence.getUser(email);
        String msg = "";
        float user_balance = user.getBalance();

        // If user can afford membership, deduct the cost from their balance and set them as a member
        if (user_balance >= 25.00 && !user.getMembership()) {

            userPersistence.modifyBalance(email, -25.00F);
            userPersistence.setMembership(email);

        } else if (("" != user.getCreditCard() && null != user.getCreditCard()) && !user.getMembership())  {

            userPersistence.setMembership(email);

        } else if (("" == user.getCreditCard() || null == user.getCreditCard())) {
            throw new MyException.EXCEPTION_NO_CARD();
        } else if (user.getMembership()) {
            throw new MyException.EXCEPTION_ITEM_ALREADY_EXIST();
        }

        return msg;
    }
}