package com.example.orderup.persistance;

import java.util.List;
import com.example.orderup.Objects.Restaurant;

public interface RestaurantPersistence {

    //Get a list of restaurant from database.
    List<Restaurant> getRestaurantSequential();

    //Insert new user comment to database.
    void insertComment(int restID, String comment);

    //Get the newest user comments.
    List<String> getComments(int id);
}
