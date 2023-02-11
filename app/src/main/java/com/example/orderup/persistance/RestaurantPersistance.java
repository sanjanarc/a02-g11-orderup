package com.example.orderup.persistance;

import java.util.List;
import com.example.orderup.Objects.Restaurant;

public interface RestaurantPersistance {


    List<Restaurant> getRestaurantSequential();

    List<Restaurant> getRestaurantRandom(Restaurant currentRestaurant); //********TO REMOVE********

    Restaurant insertRestaurant(Restaurant currentRestaurant);

    Restaurant updateRestaurant(Restaurant currentRestaurant);

    void deleteRestaurant(Restaurant currentRestaurant);
}
