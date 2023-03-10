package com.example.orderup.logic;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;

import java.util.List;

public class RestaurantServices {

    static RestaurantPersistence restaurantPersistence = Services.getRestaurantPersistence();

    public static List<Restaurant> getRestList(){
        return restaurantPersistence.getRestaurantSequential();
    }
}
