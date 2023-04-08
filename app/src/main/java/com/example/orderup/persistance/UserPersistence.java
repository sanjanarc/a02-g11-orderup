package com.example.orderup.persistance;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.Objects.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an interface for the user database
 */
public interface UserPersistence {

    /**
     * Get user object by email.
     *
     * @param email the user email.
     * @return an User object.
     */
    User getUser(String email);

    /**
     * Add new user object to the table.
     *
     * @param email     the user email.
     * @param password  the user account password.
     * @param firstName the user first name.
     * @param lastName  the user last name.
     */
    void addUser(String email, String password, String firstName, String lastName);

    /**
     * Add credit card info to user table.
     *
     * @param email   the user email.
     * @param cardNum the credit card number.
     * @param cvc     the credit card cvc.
     * @param expiry  the expiry date of the credit card.
     */
    void addCreditCard(String email, String cardNum, String cvc, String expiry);

    /**
     * Add the given address to the database.
     *
     * @param email   the user email.
     * @param address the user address.
     */
    void updateAddress(String email, String address);

    /**
     * Add the user cart info to database.
     *
     * @param email the user email.
     * @param cart  the user cart info.
     */
    void updateCart(String email, ArrayList cart);

    /**
     * Add or Reduce the balance from database.
     *
     * @param email   the user email.
     * @param balance the amount to remove or add.
     */
    void modifyBalance(String email, float balance);

    /**
     * Read and store the gift card data from database.
     *
     * @return List of gift card object.
     */
    List<Giftcard> getGiftCards();

    /**
     * Change the membership status of the user.
     *
     * @param email the email of the user.
     */
    void setMembership(String email);
}