package com.example.orderup.persistance;

import java.util.List;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.Objects.FoodItem;


public interface RestaurantPersistence {


    List<Restaurant> getRestaurantSequential();
    boolean insertRestaurant(Restaurant currentRestaurant);
    boolean findRestaurant(int restNum);

    void updateRestaurant(Restaurant currentRestaurant); //to be used to update ratings or send comments
    void deleteRestaurant(int currentRestaurantNum);

    //int getImg(Restaurant currentRestaurant);
}
