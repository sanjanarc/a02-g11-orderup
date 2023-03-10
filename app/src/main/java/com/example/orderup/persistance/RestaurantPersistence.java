package com.example.orderup.persistance;

import java.util.List;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.Objects.FoodItem;


public interface RestaurantPersistence {


    List<Restaurant> getRestaurantSequential();

   // List<Restaurant> getRestaurantRandom(Restaurant currentRestaurant); //********TO REMOVE********

   // Restaurant insertRestaurant(Restaurant currentRestaurant);

    //Restaurant updateRestaurant(Restaurant currentRestaurant); //to be used to update ratings or send comments

   // void deleteRestaurant(Restaurant currentRestaurant);

    //int getImg(Restaurant currentRestaurant);
}
