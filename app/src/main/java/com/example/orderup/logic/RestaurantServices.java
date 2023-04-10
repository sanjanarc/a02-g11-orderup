package com.example.orderup.logic;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;

import java.util.List;

/**
 * This class provided restaurant services to presentation layer.
 */
public class RestaurantServices {

    private final RestaurantPersistence restaurantPersistence;

    /**
     * Constructor.
     *
     * @param restaurantPersistence get the restaurant persistence from input parameter.
     */
    public RestaurantServices(RestaurantPersistence restaurantPersistence) {
        this.restaurantPersistence = restaurantPersistence;
    }

    /**
     * Get the list of restaurant from database.
     *
     * @return list of restaurant object.
     */
    public List getRestList() {
        return restaurantPersistence.getRestaurantSequential();
    }

    /**
     * Get the specific restaurant object.
     *
     * @param pos the restaurant id.
     * @return one restaurant object.
     */
    public Restaurant getRest(int pos) {
        return restaurantPersistence.getRest(pos);
    }

    /**
     * Insert new user comment to database and ask restaurant to retrieve the newest user comments.
     *
     * @param rest    the restaurant object.
     * @param comment the comment string.
     */
    public void insertComment(Restaurant rest, String comment) {
        restaurantPersistence.insertComment(rest.getRestaurantID(), comment);
        rest.updateComment();
    }

    /**
     * Restaurant call this method to get the newest user comments.
     *
     * @param restID the restaurant id.
     */
    public List getComments(int restID) {
        return restaurantPersistence.getComments(restID);
    }
}