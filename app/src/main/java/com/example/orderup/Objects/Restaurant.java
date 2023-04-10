package com.example.orderup.Objects;

import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.logic.Services;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds a single Restaurant object.
 */
public class Restaurant {

    // Restaurant attributes.
    private final int restaurantID;
    private final String restaurantName;
    private final String cityName;
    private final String newCategory;
    private final String description;
    private String imagesURL;
    private String serviceHours;
    private final List<FoodItem> menu; //menu List
    private final int num_menu_items;
    private final String location;
    private List<String> userComments;

    /**
     * Constructor.
     *
     * @param newID the new restaurant's ID.
     */
    public Restaurant(final int newID) {
        restaurantID = newID;
        restaurantName = null;
        newCategory = null;
        cityName = null;
        description = null;
        menu = null;
        num_menu_items = 0;
        location = null;
        userComments = null;
    }

    /**
     * Constructor.
     *
     * @param newID             the new restaurant's ID.
     * @param newRestaurantName the new restaurant's name.
     * @param newCategory       the new restaurant's food category.
     * @param cityName          the new restaurant's city.
     * @param description       the new restaurant's description.
     * @param num_menu_items    the amount of menu items.
     * @param location          the new restaurant's location.
     * @param image             the new restaurant's image.
     * @param hours             the restaurant services hour.
     */
    public Restaurant(int newID, String newRestaurantName, String newCategory,
                      String cityName, String description,
                      List foodList, final int num_menu_items,
                      final String location, final String image, final String hours) {
        this.menu = foodList;
        this.userComments = new ArrayList<>();
        restaurantID = newID;
        restaurantName = newRestaurantName;
        this.newCategory = newCategory;
        this.cityName = cityName;
        this.description = description;
        this.imagesURL = image;
        this.num_menu_items = num_menu_items;
        this.location = location;
        this.serviceHours = hours;
    }

    public String getRestaurantCategory() {
        return (newCategory);
    }

    public String getRestaurantDescription() {
        return (description);
    }

    public String getRestaurant_location() {
        return (location);
    }

    public String getCityName() {
        return (cityName);
    }

    public String getRestaurantName() {
        return (restaurantName);
    }

    public String getImagesURL() {
        return imagesURL;
    }

    public String getServiceHours() {
        return serviceHours;
    }

    public List<FoodItem> getMenuItems() {
        return (menu);
    }

    public FoodItem getItem(int i) {
        return menu.get(i);
    }

    public int getRestaurantID() {
        return (restaurantID);
    }

    /**
     * Method adds a user's comment left on the restaurant
     */
    public void updateComment() {
        RestaurantServices temp = new RestaurantServices(Services.getRestaurantPersistence());
        userComments = temp.getComments(restaurantID);
    }

    /**
     * Method returns list of Users' comments on the restaurant
     */
    public List<String> getUserComment() {
        updateComment();
        return userComments;
    }

    public int getNum_menu_items() {
        return num_menu_items;
    }
}