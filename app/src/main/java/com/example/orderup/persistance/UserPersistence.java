package com.example.orderup.persistance;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.Objects.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//The interface for user persistence.
public interface UserPersistence {

    //Get a table of users.
    Map<String, User> getUserTable();

    User getUser(String email);

    //Add a user object to the table.
    void addUser(String email, String password, String firstName, String lastName);

    //Add a credit card info to the specific user.
    void addCreditCard(String email, String cardNum, String cvc, String expiry);

    //Update the user address.
    void updateAddress(String email, String address);

    //Modify the user account balance.
    void modifyBalance(String email, float balance);

    //This get a list of gift card info from database.
    List<Giftcard> getGiftCards();
}
