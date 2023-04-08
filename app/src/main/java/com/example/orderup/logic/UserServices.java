package com.example.orderup.logic;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.util.ArrayList;

/**
 * This class provided method/services that related to user.
 */
public class UserServices {

    public UserPersistence userPersistence;

    /**
     * Constructor.
     *
     * @param userPersistence the user database.
     */
    public UserServices(UserPersistence userPersistence) {

        //Dependency injection.
        this.userPersistence = userPersistence;
    }

    /**
     * Return user object.
     *
     * @param email the user email
     * @return current user.
     */
    public User getUser(String email) {

        User user = userPersistence.getUser(email);

        if (user != null) {
            return user;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Add the user cart info to database.
     *
     * @param email the user email.
     * @param cart  the user cart info.
     */
    public void updateCart(String email, ArrayList cart) {
        userPersistence.updateCart(email, cart);
    }

    public static Boolean getMembership(String email)
    {
        User user = userPersistence.getUserTable().get(email);


        return user.getMembership();

    }

    public static Boolean getMembership(String email)
    {
        User user = userPersistence.getUserTable().get(email);


        return user.getMembership();

    }
}
