package com.example.orderup.persistance;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.Objects.User;

import java.util.HashMap;

//The interface for user persistence.
public interface UserPersistence {

    //Get a table of users.
    HashMap<String, User> getUserTable();

    //Add a user object to the table.
    void addUser(String email, String password, String firstName, String lastName);

    //Add a credit card info to the specific user.
    void addCreditCard(String email, String cardNum, String cvc, String expiry);

    //Update the user address.
    void updateAddress(String email, String address);

    //Modify the user account balance.
    void modifyBalance(String email, float balance);

    //This get a list of giftcard info from database.
    Giftcard[] getGiftcards();

    void setMembership(String email);
}
