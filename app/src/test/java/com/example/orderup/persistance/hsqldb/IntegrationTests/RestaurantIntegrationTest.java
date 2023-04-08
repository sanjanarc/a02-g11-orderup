package com.example.orderup.persistance.hsqldb.IntegrationTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;


import java.io.File;
import java.io.IOException;

import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.Objects.FoodItem;
import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.persistance.hsqldb.RestaurantPersistenceHSQLDB;
import com.example.orderup.utils.TestFilesUtil;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class RestaurantIntegrationTest {
    private RestaurantServices restaurantServices;
    private RestaurantPersistence restaurantPersistence;
    private File tempDB;

    @Before
    public void setUp() throws IOException{
        this.tempDB= TestFilesUtil.copyDB();
        this.restaurantPersistence= new RestaurantPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        this.restaurantServices= new RestaurantServices(restaurantPersistence);
    }
    @Test
    public void testListRestaurants() {
        Restaurant restaurant;
        restaurant= restaurantServices.getSequential();
        assertNotNull("first sequential course should not be null");
        assertTrue("Indian Food Corner".equals(restaurant.getRestaurantName()));

        //get second restaurant in the list
        restaurant= restaurantServices.getSequential();
        int menuItems= restaurant.getNum_menu_items();
        assertNotNull("next sequential restaurant should also not be null", restaurant);
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

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }


}
