package com.example.orderup.persistance;

import com.example.orderup.Objects.User;

import java.util.HashMap;

public interface UserPersistence {

    HashMap<String, User> getUserList();

    void addUser(String email, String firstName, String lastName, String password);

    void addCreditCard(String email, String cardNum, String cvc, String expiry);

    void updateFirstName(String email, String firstName);

    void updateLastName(String email, String lastName);

    void updatePassword(String email, String password);

    void updateAddress(String email, String address);

    void addBalance(String email, float balance);

    String getBalance(String email);

    User insertUser(final User currentUser);

    User updateUser(final User currentUser);

    void deleteUser(final User currentUser);

}
