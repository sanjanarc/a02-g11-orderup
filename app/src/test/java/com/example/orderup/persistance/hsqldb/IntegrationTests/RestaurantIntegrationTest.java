package com.example.orderup.persistance.hsqldb.IntegrationTests;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.Objects.FoodItem;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RestaurantIntegrationTest {
    private RestaurantServices restaurantServices;
    private RestaurantPersistence restaurantPersistence;

    @Before
    public void setUp(){
        restaurantPersistence= mock(RestaurantPersistence.class);
        restaurantServices= new RestaurantServices(restaurantPersistence);
    }
    @Test
    public void test1() {
        final Restaurant restaurant;
        System.out.println("\nStarting test RestaurantIntegration");
        final List<Restaurant> restaurants= new ArrayList<>();
        restaurants.add(new Restaurant(2, "Baked Expectations", "Pie,Dessert,Sweet,Cake,Cookies,Cupcakes", "Winnipeg", "", new FoodItem(2,1, "Fresh Strawberry Cheesecake",10.00, "https://cdn.doordash.com/media/photos/42984140-591d-4fd4-a326-5c1711c50564-retina-large-jpeg","Delicious, juicy, plump strawberries heaped atop a creamy plain cheesecake."), new FoodItem(2,2, "Cherry Royale Cheesecake", 9.75, "https://cdn.doordash.com/media/photos/0e61f405-b144-46d2-87c9-baf68c57d9ec-retina-large-jpeg", "The classic, plain (if you can call it that!) cheesecake with tons of dark cherry topping."),new FoodItem(2,3, "Oreo Cookie Cheesecake", 9.75,"https://cdn.doordash.com/media/photos/0ff3f07d-1fd8-49e7-9868-f4d95c9224dd-retina-large-jpeg", "What can we say – everyone loves it. Chocolate cookie crust, Oreos generously mixed through the cheesecake – topped with more cookies and cream." ), 3, "161 Osborne St Winnipeg MB R3L 1Y7","image" ));

        when(restaurantPersistence.getRestaurantSequential()).thenReturn(restaurants);
        restaurant= restaurantServices.getRestList();


    }

}
