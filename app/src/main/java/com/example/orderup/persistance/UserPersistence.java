package com.example.orderup.persistance;

import com.example.orderup.Objects.User;

import java.util.HashMap;

public interface UserPersistence {

    HashMap<String, User> getUserList();

    void addUser(String email, User newUser);

    void addCreditCard(String email, int cardNum, int cvc, String expiry);

    void updateFirstName(String email, String firstName);

    void updateLastName(String email, String lastName);

    void updatePassword(String email, String password);

    void updateAddress(String email, String address);

    void addBalance(String email, float balance);

    String getBalance(String email);
}
