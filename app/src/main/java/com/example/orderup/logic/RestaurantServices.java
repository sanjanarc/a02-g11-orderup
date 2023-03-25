package com.example.orderup.logic;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;

import java.util.List;

public class RestaurantServices extends Services{

    private static final RestaurantPersistence restaurantPersistence = getRestaurantPersistence();

    public static List<Restaurant> getRestList(){
        return restaurantPersistence.getRestaurantSequential();
    }

    public static Restaurant getRest(int pos){
        return restaurantPersistence.getRestaurantSequential().get(pos);
    }
}
