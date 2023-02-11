package com.example.orderup.Objects;

import java.util.Objects;

public class Restaurant {

    private final String restaurantID;
    private final String restaurantName;
    private static final String cityName= "Winnipeg";
    private final String newCategory;
    private final String description;
    private final int num_ratings;
    private final int average_rating;
    private final String imagesURL;
    private final String menu_items;
    private final int num_menu_items;
    private final String location;


    public Restaurant(final String newID)
    {
        restaurantID = newID;
        restaurantName = null;
        newCategory= null;
        description= null;
        num_ratings= 0;
        average_rating= 0;
        imagesURL= null;
        menu_items= null;
        num_menu_items=0;
        location= null;
    }

    public Restaurant(final String newID, final String newRestaurantName, final String newCategory, final String description, final int num_ratings, final int average_rating, final String imagesURL, final String menu_items, final int num_menu_items, final String location )
    {
        restaurantID = newID;
        restaurantName = newRestaurantName;
        this.newCategory= newCategory;
        this.description= description;
        this.num_ratings= num_ratings;
        this.average_rating= average_rating;
        this.imagesURL= imagesURL;
        this.menu_items= menu_items;
        this.num_menu_items=num_menu_items;
        this.location= location;

    }
    public String getRestaurantCategory(){
        return(newCategory);
    }
    public String getRestaurantDescription(){
        return(description);
    }
    public int getRestaurant_numRatings(){
        return(num_ratings);

    }
    public int getRestaurant_avgRatings(){
        return(average_rating);
    }
    public String getFood_imageURL(){
        return (imagesURL);
    }
    public String getMenuItems(){
        return(menu_items);
    }
    public int getNum_menuItems(){
        return(num_menu_items);
    }
    public String getRestaurant_location(){
        return(location);
    }
    public String getCityName(){
        return(cityName);
    }

    public String getRestaurantID()
    {
        return (restaurantID);
    }

    public String getRestaurantName()
    {
        return (restaurantName);
    }

    public String toString()
    {
        return String.format("Restauant id: %s, Restaurant name: %s", restaurantID, restaurantName);
    }

    public boolean equals(Object other)
    {
        boolean equals = false;
        if (other instanceof Restaurant) {

            final Restaurant otherRestaurant = (Restaurant) other;
            equals = Objects.equals(this.restaurantID, otherRestaurant.restaurantID);

        }

        return equals;
    }
}