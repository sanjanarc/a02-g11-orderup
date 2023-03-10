package com.example.orderup.persistance;

import java.util.List;
import com.example.orderup.Objects.Restaurant;

public interface RestaurantPersistence {

    //Get a list of restaurant from database.
    List<Restaurant> getRestaurantSequential();

    //Insert new restaurant to database.
    boolean insertRestaurant(Restaurant currentRestaurant);

    //Go through the database and search for a specific restaurant.
    boolean findRestaurant(int restNum);

    //Remove a specific restaurant from database.
    void deleteRestaurant(int currentRestaurantNum);
}
