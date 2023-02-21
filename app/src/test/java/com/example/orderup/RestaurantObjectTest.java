package com.example.orderup;

import com.example.orderup.Objects.Restaurant;

import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantObjectTest {
    @Test
    public void testRestaurant1() {

        Restaurant restaurant;

        System.out.println("\nStarting testCourse");

        restaurant = new Restaurant("Restaurant 2", "Baked Expectations", "Pie,Dessert,Sweet,Cake,Cookies,Cupcakes", "", 0, 0, "", "", 0, "");

        assertNotNull(restaurant);
        assertTrue("Restaurant 2".equals(restaurant.getRestaurantID()));
        assertTrue("Baked Expectations".equals(restaurant.getRestaurantName()));

        System.out.println("Finished testing Restaurant Object class");
    }
}
