package com.example.orderup.Objects;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RestaurantTest {

     Restaurant restaurant;

     final int restaurantID = 1;
     final String restaurantName = "Test Restaurant";
     final String newCategory = "Test Category";
     final String cityName = "Test City";
     final String description = "Test Description";
     final String image = "Test Image URL";
     final int num_menu_items = 3;
     final String location = "Test Location";

     final FoodItem item1 = new FoodItem(1,1,"Test Food 1",
            0, "image", "Test Food Description 1");
     final FoodItem item2 = new FoodItem(1,2, "Test Food 2",0, "image", "Food Description 2");
     final FoodItem item3 = new FoodItem(1,2, "Test Food 3", 0, "image", "Test Food Description 3");


    public void setUp() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);

        restaurant = new Restaurant(restaurantID, restaurantName, newCategory, cityName,
                description, item1, item2, item3, num_menu_items, location, image);
    }

    @Test
    public void testRestaurantConstructor() {
        int expectedID = 1;
        String expectedName = null;
        String expectedCityName = null;
        String expectedDescription = null;
        List<FoodItem> expectedMenu = null;
        FoodItem expectedItem1 = null;
        FoodItem expectedItem2 = null;
        FoodItem expectedItem3 = null;
        String expectedLocation = null;

        Restaurant restaurant = new Restaurant(expectedID);

        assertEquals(expectedID, restaurant.getRestaurantID());
        assertEquals(expectedName, restaurant.getRestaurantName());
        assertEquals(expectedCityName, restaurant.getCityName());
        assertEquals(expectedDescription, restaurant.getRestaurantDescription());
        assertEquals(expectedMenu, restaurant.getMenuItems());
        assertEquals(expectedItem1, restaurant.getItem1());
        assertEquals(expectedItem2, restaurant.getItem2());
        assertEquals(expectedItem3, restaurant.getItem3());
        assertEquals(expectedLocation, restaurant.getRestaurant_location());
    }

    @Test
    public void testRestaurantRConstructor() {
        // Create three food items for the restaurant
        final FoodItem item1 = new FoodItem(1,1,"Test Food 1",
                0, "image", "Test Food Description 1");
        final FoodItem item2 = new FoodItem(1,2, "Test Food 2",0, "image", "Food Description 2");
        final FoodItem item3 = new FoodItem(1,2, "Test Food 3", 0, "image", "Test Food Description 3");

        // Create a new restaurant
        Restaurant restaurant = new Restaurant(1, "McDonald's", "Fast Food", "New York City",
                "The world's largest chain of hamburger fast food restaurants",
                item1, item2, item3, 3, "123 Main Street", "https://example.com/mcdonalds.jpg");

        // Test that the restaurant was created with the correct values
        assertEquals(1, restaurant.getRestaurantID());
        assertEquals("McDonald's", restaurant.getRestaurantName());
        assertEquals("Fast Food", restaurant.getRestaurantCategory());
        assertEquals("New York City", restaurant.getCityName());
        assertEquals("The world's largest chain of hamburger fast food restaurants", restaurant.getRestaurantDescription());
        assertEquals(3, restaurant.getMenuItems().size());
        assertEquals("123 Main Street", restaurant.getRestaurant_location());
        assertEquals("https://example.com/mcdonalds.jpg", restaurant.getImagesURL());

        // Test that the menu was created with the correct food items
        List<FoodItem> menuItems = restaurant.getMenuItems();
        assertEquals(3, menuItems.size());
        assertEquals("Burger", menuItems.get(0).getItemName());
        assertEquals(8.99, menuItems.get(0).getItemPrice(), 0.001);
        assertEquals("Fries", menuItems.get(1).getItemName());
        assertEquals(3.99, menuItems.get(1).getItemPrice(), 0.001);
        assertEquals("Drink", menuItems.get(2).getItemName());
        assertEquals(1.99, menuItems.get(2).getItemPrice(), 0.001);

        // Test that individual food items can be retrieved from the restaurant
        assertEquals("Burger", restaurant.getItem1().getItemName());
        assertEquals(8.99, restaurant.getItem1().getItemPrice(), 0.001);
        assertEquals("Fries", restaurant.getItem2().getItemName());
        assertEquals(3.99, restaurant.getItem2().getItemPrice(), 0.001);
        assertEquals("Drink", restaurant.getItem3().getItemName());
        assertEquals(1.99, restaurant.getItem3().getItemPrice(), 0.001);
    }


    @Test
    public void testGetRestaurantDescription() {
        String desc = restaurant.getRestaurantDescription();
        assertEquals(description, desc);
    }

    @Test
    public void testGetMenuItems() {
        List<FoodItem> menu = restaurant.getMenuItems();
        assertEquals(3, menu.size());
        assertEquals(item1, menu.get(0));
        assertEquals(item2, menu.get(1));
        assertEquals(item3, menu.get(2));
    }

    @Test
    public void testGetRestaurant_location() {
        String loc = restaurant.getRestaurant_location();
        assertEquals(location, loc);
    }

    @Test
    public void testGetCityName() {
        String city = restaurant.getCityName();
        assertEquals(cityName, city);
    }

    @Test
    public void testGetRestaurantID() {
        int id = restaurant.getRestaurantID();
        assertEquals(restaurantID, id);
    }

    @Test
    public void testGetRestaurantName() {
        String name = restaurant.getRestaurantName();
        assertEquals(restaurantName, name);
    }

    @Test
    public void testGetImagesURL() {
        String url = restaurant.getImagesURL();
        assertEquals(image, url);
    }
}