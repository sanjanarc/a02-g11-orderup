package com.example.orderup.persistance;

import com.example.orderup.Objects.Restaurant;

import java.util.List;

/**
 * This class is an interface for the restaurant database
 */
public interface RestaurantPersistence {

    /**
     * Get a list of restaurant from database.
     *
     * @return a list of Restaurant objects.
     */
    List<Restaurant> getRestaurantSequential();

    /**
     * Get restaurant object by id.
     *
     * @param id the user id.
     * @return a Restaurant object.
     */
    Restaurant getRest(int id);

    //Insert new user comment to database.
    /**
     * Insert new user comment to database.
     *
     * @param restID the restaurant id.
     * @param comment the comment string.
     */
    void insertComment(int restID, String comment);

    /**
     * Get the newest user comments.
     *
     * @param id the restaurant id.
     * @return a list of comment strings.
     */
    List<String> getComments(int id);
}
