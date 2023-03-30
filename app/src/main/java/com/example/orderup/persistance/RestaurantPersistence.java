package com.example.orderup.persistance;

import java.util.List;
import com.example.orderup.Objects.Restaurant;

public interface RestaurantPersistence {

    //Get a list of restaurant from database.
    List<Restaurant> getRestaurantSequential();

    void updateComment(int id, String comment);

    List<String> getComments(int id);
}
