package com.example.orderup.persistance;

import com.example.orderup.Objects.Restaurant;

import java.util.List;

/**
 * This class is an interface for the restaurant database
 */
public interface RestaurantPersistence {

    //Get a list of restaurant from database.
    List<Restaurant> getRestaurantSequential();

    Restaurant getRest(int id);

    //Insert new user comment to database.
    void insertComment(int restID, String comment);

    //Get the newest user comments.
    List<String> getComments(int id);
}
