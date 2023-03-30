package com.example.orderup.Objects;
import com.example.orderup.R;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
/*
Restaurant: This object class represents a restaurant in the database. Its menu consists of "FoodItem" objects
 */
public class Restaurant {

    private final int restaurantID;
    private final String restaurantName;
    private final String cityName;
    private final String newCategory;
    private final String description;
    private String imagesURL;
    private final List<FoodItem> menu; //menu List
    private final FoodItem item1; //FoodItems in a menu List
    private final FoodItem item2;
    private final FoodItem item3;
    private int num_menu_items;
    private final String location;


    public Restaurant(final int newID) {
        restaurantID = newID;
        restaurantName = null;
        newCategory = null;
        cityName = null;
        description = null;
        menu = null;
        item1 = null;
        item2 = null;
        item3 = null;
        num_menu_items = 0;
        location = null;
    }

    public Restaurant(int newID, String newRestaurantName, String newCategory,
                      String cityName, String description,
                      final FoodItem item1, final FoodItem item2,
                      final FoodItem item3, final int num_menu_items,
                      final String location, final String image) {
        this.menu = new ArrayList<>();

        restaurantID = newID;
        restaurantName = newRestaurantName;
        this.newCategory = newCategory;
        this.cityName = cityName;
        this.description = description;
        this.imagesURL = image;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;

        this.num_menu_items = num_menu_items;

        this.location = location;

        //add the FoodItems to menu List
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);

    }
    /*
    Accessor and mutator methods
     */
    public String getRestaurantCategory() {
        return (newCategory);
    }

    public String getRestaurantDescription() {
        return (description);
    }

    public List<FoodItem> getMenuItems() {
        return (menu);
    }

    public FoodItem getItem1() {
        return item1;
    }

    public FoodItem getItem2() {
        return item2;
    }

    public FoodItem getItem3() {
        return item3;
    }


    public String getRestaurant_location() {
        return (location);
    }

    public String getCityName() {
        return (cityName);
    }

    public int getRestaurantID() {
        return (restaurantID);
    }
    public String getRestaurantName() {
        return (restaurantName);
    }
    public String getImagesURL() {
        return imagesURL;
    }

    public int getNum_menuItem(){
        return num_menu_items;
    }
}
