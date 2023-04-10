package com.example.orderup.logic;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.util.ArrayList;
import java.util.List;

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
     * Adds user cart info to database
     *
     * @param email        the user's email.
     * @param rest_id      the restaurant id of the food item.
     * @param food_id      the id of the food item.
     * @param quantity     the amount of the food item added to cart.
     *
     */
    public void updateCart(String email,int rest_id, int food_id, int quantity) {
        userPersistence.updateCart(email, rest_id,food_id,quantity);
    }


    /**
     * Removes user cart info from database
     *
     * @param email        the user's email.
     * @param rest_id      the restaurant id of the food item.
     * @param food_id      the id of the food item.
     * @param quantity     the amount of the food item added to cart.
     *
     */
    public void removeFromCart(String email, int rest_id, int food_id, int quantity) {
        userPersistence.removeFromCart(email,rest_id,food_id,quantity);
    }

    /**
     * Clears all items from cart in the database.
     *
     * @param email   the user's email.
     */
    public void clearCart(String email) {
        userPersistence.clearCart(email);
    }


    /**
     * User calls this method to get their cart
     *
     * @param email the user's email.
     */
    public List<FoodItem> getFoodCart(String email) {
        return userPersistence.getFoodCart(email);
    }
}
