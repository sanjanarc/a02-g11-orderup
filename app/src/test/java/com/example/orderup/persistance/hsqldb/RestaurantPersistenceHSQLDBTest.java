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
        System.out.println("\nStarting test RestaurantSequential");
        final List<Restaurant> restaurants= new ArrayList<>();
        restaurants.add(new Restaurant(2, "Baked Expectations", "Pie,Dessert,Sweet,Cake,Cookies,Cupcakes", "Winnipeg", "", new FoodItem(2,1, "Fresh Strawberry Cheesecake",10.00, "https://cdn.doordash.com/media/photos/42984140-591d-4fd4-a326-5c1711c50564-retina-large-jpeg","Delicious, juicy, plump strawberries heaped atop a creamy plain cheesecake."), new FoodItem(2,2, "Cherry Royale Cheesecake", 9.75, "https://cdn.doordash.com/media/photos/0e61f405-b144-46d2-87c9-baf68c57d9ec-retina-large-jpeg", "The classic, plain (if you can call it that!) cheesecake with tons of dark cherry topping."),new FoodItem(2,3, "Oreo Cookie Cheesecake", 9.75,"https://cdn.doordash.com/media/photos/0ff3f07d-1fd8-49e7-9868-f4d95c9224dd-retina-large-jpeg", "What can we say – everyone loves it. Chocolate cookie crust, Oreos generously mixed through the cheesecake – topped with more cookies and cream." ), 3, "161 Osborne St Winnipeg MB R3L 1Y7","image","9" ));

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
        final Restaurant restaurant;
        System.out.println("\nStarting test RestaurantIntegration");
        final List<Restaurant> restaurants= new ArrayList<>();
        final FoodItem item1= new FoodItem(1,1,"IFC Spl Saag & Makki Di Roti Meal",999.00,"food_1_1","Saag and two makki di rotis with salted lassi.");
        final FoodItem item2= new FoodItem(1,2,"Vegetarian Combo",1299.00,"food_1_2","Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle.");

        final FoodItem item3= new FoodItem(1,3,"Pani Puri",699.00,"food_1_3","Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions.");
        restaurant= new Restaurant(1,"Indian Food Corner","Tazndoori,Indian, Chicken","Winnipeg","Traditional Indian dishes from butter chicken to chicken biryani.",item1, item2, item3, 3,"1373 Pembina Hwy #4 Winnipeg MB R3T 2B7","indianfoodcorner_home","9");
        restaurants.add(restaurant);
        when(restaurantPersistence.getRestaurantSequential()).thenReturn(restaurants);

        assertTrue("Restaurant List should not be empty.",!restaurantServices.getRestList().isEmpty());
    }
    @Test
    public void testGetRestaurantID(){
        final Restaurant restaurant;
        System.out.println("\nStarting test RestaurantIntegration");
        final List<Restaurant> restaurants= new ArrayList<>();
        final FoodItem item1= new FoodItem(1,1,"IFC Spl Saag & Makki Di Roti Meal",999.00,"food_1_1","Saag and two makki di rotis with salted lassi.");
        final FoodItem item2= new FoodItem(1,2,"Vegetarian Combo",1299.00,"food_1_2","Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle.");

        final FoodItem item3= new FoodItem(1,3,"Pani Puri",699.00,"food_1_3","Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions.");
        restaurant= new Restaurant(1,"Indian Food Corner","Tazndoori,Indian, Chicken","Winnipeg","Traditional Indian dishes from butter chicken to chicken biryani.",item1, item2, item3, 3,"1373 Pembina Hwy #4 Winnipeg MB R3T 2B7","indianfoodcorner_home", "8");
        restaurants.add(restaurant);
        when(restaurantPersistence.getRest(1)).thenReturn(restaurant);

        assertTrue("Restaurant at 1 should return 1 as its ID",1  == restaurantServices.getRest(1).getRestaurantID());
    }



}
