package com.example.orderup.logic;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;

import java.util.List;

public class RestaurantServices extends Services {

    private static final RestaurantPersistence restaurantPersistence = getRestaurantPersistence();

    public static List<Restaurant> getRestList() {
        return restaurantPersistence.getRestaurantSequential();
    }

    public static Restaurant getRest(int pos) {
        return restaurantPersistence.getRestaurantSequential().get(pos);
    }

    //Insert new user comment to database and ask restaurant to retrieve the newest user comments.
    public static void insertComment(Restaurant rest, String comment) {
        restaurantPersistence.insertComment(rest.getRestaurantID(), comment);
        rest.updateComment();
    }

    //Restaurant call this method to get the newest user comments.
    public static List<String> getComments(int restID) {
        return restaurantPersistence.getComments(restID);
    }
}
