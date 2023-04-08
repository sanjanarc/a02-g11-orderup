package com.example.orderup.Objects;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
                description, item1, item2, item3, num_menu_items, location, image, "8");
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
                item1, item2, item3, 3, "123 Main Street", "https://example.com/mcdonalds.jpg", "8");

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
        assertEquals("Test Food 1", menuItems.get(0).getItemName());
        assertEquals(0, menuItems.get(0).getItemPrice(), 0.001);
        assertEquals("Test Food 2", menuItems.get(1).getItemName());
        assertEquals(0, menuItems.get(1).getItemPrice(), 0.001);
        assertEquals("Test Food 3", menuItems.get(2).getItemName());
        assertEquals(0, menuItems.get(2).getItemPrice(), 0.001);

        // Test that individual food items can be retrieved from the restaurant
        assertEquals("Test Food 1", restaurant.getItem1().getItemName());
        assertEquals(0, restaurant.getItem1().getItemPrice(), 0.001);
        assertEquals("Test Food 2", restaurant.getItem2().getItemName());
        assertEquals(0, restaurant.getItem2().getItemPrice(), 0.001);
        assertEquals("Test Food 3", restaurant.getItem3().getItemName());
        assertEquals(0, restaurant.getItem3().getItemPrice(), 0.001);
    }

  @Test
  public void testGetRestaurantID() {
      Restaurant restaurant = new Restaurant(1, "Restaurant A", "City A", "Category A", "Description A", item1, item2, item3, 3, "Location A", "image", "8");
      int expectedId = 1;
      int actualId = restaurant.getRestaurantID();
      assertEquals(expectedId, actualId);
  }

    @Test
    public void testGetRestaurantName() {
        Restaurant restaurant = new Restaurant(2, "Restaurant B", "City B", "Category B", "Description B", item1, item2, item3, 3, "Location B", "image","8");
        String expectedName = "Restaurant B";
        String actualName = restaurant.getRestaurantName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetRestaurantCategory() {
        Restaurant restaurant = new Restaurant(3, "Restaurant C", "Category C", "City C", "Description C", item1, item2, item3, 3, "Location C", "image","8");
        String expectedCategory = "Category C";
        String actualCategory = restaurant.getRestaurantCategory();
        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    public void testGetRestaurantDescription() {
        Restaurant restaurant = new Restaurant(4, "Restaurant D", "City D", "Category D", "Description D", item1, item2, item3, 3, "Location D", "image","8");
        String expectedDescription = "Description D";
        String actualDescription = restaurant.getRestaurantDescription();
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testGetMenuItems() {
        FoodItem item1 = new FoodItem(1,1,"Test Food 1",
                0, "image", "Test Food Description 1");
        FoodItem item2 = new FoodItem(1,2, "Test Food 2",0, "image", "Food Description 2");
        FoodItem item3 = new FoodItem(1,2, "Test Food 3", 0, "image", "Test Food Description 3");
        List<FoodItem> menu = Arrays.asList(item1, item2, item3);
        Restaurant restaurant = new Restaurant(2, "Restaurant B", "City B", "Category B", "Description B", item1, item2, item3, 3, "Location B", "image","8");
        List<FoodItem> expectedMenu = menu;
        List<FoodItem> actualMenu = restaurant.getMenuItems();
        assertEquals(expectedMenu, actualMenu);
    }

    @Test
    public void testGetItem1() {
        FoodItem item1 = new FoodItem(1,1,"Test Food 1",
                0, "image", "Test Food Description 1");
        FoodItem item2 = new FoodItem(1,2, "Test Food 2",0, "image", "Food Description 2");
        FoodItem item3 = new FoodItem(1,2, "Test Food 3", 0, "image", "Test Food Description 3");
        List<FoodItem> menu = Arrays.asList(item1, item2, item3);
        Restaurant restaurant = new Restaurant(2, "Restaurant B", "City B", "Category B", "Description B", item1, item2, item3, 3, "Location B", "image","8");
        FoodItem expectedItem1 = item1;
        FoodItem actualItem1 = restaurant.getItem1();
        assertEquals(expectedItem1, actualItem1);
    }

    @Test
    public void testGetItem2() {
        FoodItem item1 = new FoodItem(1,1,"Test Food 1",
                0, "image", "Test Food Description 1");
        FoodItem item2 = new FoodItem(1,2, "Test Food 2",0, "image", "Food Description 2");
        FoodItem item3 = new FoodItem(1,2, "Test Food 3", 0, "image", "Test Food Description 3");
        List<FoodItem> menu = Arrays.asList(item1, item2, item3);
        Restaurant restaurant = new Restaurant(2, "Restaurant B", "City B", "Category B", "Description B", item1, item2, item3, 3, "Location B", "image","8");
        FoodItem expectedItem = item2;
        FoodItem actualItem = restaurant.getItem2();
        assertEquals(expectedItem, actualItem);
    }



    @Test
    public void testGetItem3() {
        FoodItem item1 = new FoodItem(1,1,"Test Food 1",
                0, "image", "Test Food Description 1");
        FoodItem item2 = new FoodItem(1,2, "Test Food 2",0, "image", "Food Description 2");
        FoodItem item3 = new FoodItem(1,2, "Test Food 3", 0, "image", "Test Food Description 3");
        List<FoodItem> menu = new ArrayList<>(Arrays.asList(item1, item2, item3));
        Restaurant restaurant = new Restaurant(2, "Restaurant B", "City B", "Category B", "Description B", item1, item2, item3, 3, "Location B", "image","8");
        FoodItem expectedItem = item3;
        FoodItem actualItem = restaurant.getItem3();
        assertEquals(expectedItem, actualItem);
    }

    @Test
    public void testGetRestaurantLocation() {
        List<FoodItem> menu = new ArrayList<>();
        Restaurant restaurant = new Restaurant(2, "Restaurant B", "City B", "Category B", "Description B", item1, item2, item3, 3, "456 State St", "image","8");
        String expectedLocation = "456 State St";
        String actualLocation = restaurant.getRestaurant_location();
        assertEquals(expectedLocation, actualLocation);
    }

}