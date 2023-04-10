package com.example.orderup.Objects;

import org.junit.Test;
import static org.junit.Assert.*;

public class FoodItemTest {

    @Test
    public void testFoodItemConstructor() {
        int restaurantId = 1;
        int itemId = 1;
        String itemName = "Burger";
        double itemPrice = 10.0;
        String itemImageURL = "https://example.com/burger.png";
        String itemDescription = "A delicious burger.";

        FoodItem foodItem = new FoodItem(restaurantId, itemId, itemName, itemPrice, itemImageURL, itemDescription);

        assertEquals(restaurantId, foodItem.getRestaurant_id());
        //assertEquals(itemId, foodItem.getItem_id());
        assertEquals(itemName, foodItem.getItemName());
        assertEquals(itemPrice, foodItem.getItemPrice(), 0.001);
        assertEquals(itemImageURL, foodItem.getImageUrl());
        assertEquals(itemDescription, foodItem.getItemDescription());
    }


    @Test
    public void testGetRestaurantId() {
        FoodItem item = new FoodItem(1, 2, "Pizza", 9.99, "http://example.com/pizza.jpg", "Delicious pizza");
        int expectedId = 1;
        int actualId = item.getRestaurant_id();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetItemName() {
        FoodItem item = new FoodItem(1, 2, "Pizza", 9.99, "http://example.com/pizza.jpg", "Delicious pizza");
        String expectedName = "Pizza";
        String actualName = item.getItemName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetItemPrice() {
        FoodItem item = new FoodItem(1, 2, "Pizza", 9.99, "http://example.com/pizza.jpg", "Delicious pizza");
        double expectedPrice = 9.99;
        double actualPrice = item.getItemPrice();
        assertEquals(expectedPrice, actualPrice, 0.0);
    }

    @Test
    public void testGetImageUrl() {
        FoodItem item = new FoodItem(1, 2, "Pizza", 9.99, "http://example.com/pizza.jpg", "Delicious pizza");
        String expectedUrl = "http://example.com/pizza.jpg";
        String actualUrl = item.getImageUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testGetItemDescription() {
        FoodItem item = new FoodItem(1, 2, "Pizza", 9.99, "http://example.com/pizza.jpg", "Delicious pizza");
        String expectedDescription = "Delicious pizza";
        String actualDescription = item.getItemDescription();
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void getItem_id() {
        FoodItem foodItem = new FoodItem(1, 2, "Pizza", 9.99, "https://example.com/pizza.jpg", "Delicious pizza");
        int itemId = foodItem.getItem_id();
        assertEquals(2, itemId);
    }

    @Test
    public void getRestaurant_id() {
        FoodItem foodItem = new FoodItem(1, 2, "Pizza", 9.99, "https://example.com/pizza.jpg", "Delicious pizza");
        int restaurantId = foodItem.getRestaurant_id();
        assertEquals(1, restaurantId);
    }

    @Test
    public void getNumItems() {
        FoodItem foodItem = new FoodItem(1, 1, "Pizza", 10.99, "https://example.com/pizza.jpg", "Delicious pizza");
        assertEquals(0, foodItem.getNumItems());
    }

    @Test
    public void setNumItems() {
        FoodItem foodItem = new FoodItem(1, 1, "Pizza", 10.99, "https://example.com/pizza.jpg", "Delicious pizza");
        foodItem.setNumItems(2);
        assertEquals(2, foodItem.getNumItems());
    }


    @Test
    public void testDefaultConstructor() {
        FoodItem item = new FoodItem();
        assertEquals(0, item.getRestaurant_id());
        assertEquals(0, item.getItem_id());
        assertEquals("Default Name", item.getItemName());
        assertEquals("Default Description", item.getItemDescription());
        assertEquals(0.0, item.getItemPrice(), 0.001);
        assertEquals("", item.getImageUrl());
        assertEquals(0, item.getNumItems());
    }
}
