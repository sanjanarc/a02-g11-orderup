package com.example.orderup.persistance.hsqldb;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.logic.Services;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;

public class RestaurantPersistenceHSQLDBTest {

    private RestaurantServices restaurantServices;
    private RestaurantPersistence restaurantPersistence;

    @Before
    public void setUp() throws SQLException {
        //mock of Restaurant Persistence created
        restaurantPersistence= mock(RestaurantPersistence.class);
        restaurantServices= new RestaurantServices(restaurantPersistence);
    }

    @Test
    public void testGetRestaurantSequential() throws SQLException {
        // Set up the class under test with the mocked connection
        final Restaurant restaurant;
        System.out.println("\nStarting test RestaurantIntegration");
        final List<Restaurant> restaurants= new ArrayList<>();
        restaurants.add(new Restaurant(2, "Baked Expectations", "Pie,Dessert,Sweet,Cake,Cookies,Cupcakes", "Winnipeg", "", new FoodItem(2,1, "Fresh Strawberry Cheesecake",10.00, "https://cdn.doordash.com/media/photos/42984140-591d-4fd4-a326-5c1711c50564-retina-large-jpeg","Delicious, juicy, plump strawberries heaped atop a creamy plain cheesecake."), new FoodItem(2,2, "Cherry Royale Cheesecake", 9.75, "https://cdn.doordash.com/media/photos/0e61f405-b144-46d2-87c9-baf68c57d9ec-retina-large-jpeg", "The classic, plain (if you can call it that!) cheesecake with tons of dark cherry topping."),new FoodItem(2,3, "Oreo Cookie Cheesecake", 9.75,"https://cdn.doordash.com/media/photos/0ff3f07d-1fd8-49e7-9868-f4d95c9224dd-retina-large-jpeg", "What can we say – everyone loves it. Chocolate cookie crust, Oreos generously mixed through the cheesecake – topped with more cookies and cream." ), 3, "161 Osborne St Winnipeg MB R3L 1Y7","image" ));

        when(restaurantPersistence.getRestaurantSequential()).thenReturn(restaurants);
        restaurant= restaurantServices.getSequential();

        int menuItems= restaurant.getNum_menu_items();
        assertNotNull("first sequential restaurant should not be null", restaurant);
        assertNotNull("restaurant should have 3 menu items", 3== restaurant.getNum_menu_items());
        assertTrue("Baked Expectations".equals(restaurant.getRestaurantName()));
        assertNotNull("restaurant's first menu item should not be null", restaurant.getItem1());
        assertTrue("Fresh Strawberry Cheesecake".equals(restaurant.getItem1().getItemName()));
        assertTrue("Cherry Royale Cheesecake".equals(restaurant.getItem2().getItemName()));
        assertTrue("Oreo Cookie Cheesecake".equals(restaurant.getItem3().getItemName()));

    }
    @Test
    public void testGetRestaurant(){
        assertTrue("Restaurant List should not be empty.",!restaurantServices.getRestList().isEmpty());
    }
    @Test
    public void testGetRestaurantID(){
        assertTrue("Restaurant at position 2 should return 2 as its ID", 2 == restaurantServices.getRest(2).getRestaurantID());
    }



}
