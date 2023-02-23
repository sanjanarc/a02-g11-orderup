package com.example.orderup.Objects;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Restaurant {

    private final String restaurantID;
    private final String restaurantName;
    private static final String cityName= "Winnipeg";
    private final String newCategory;
    private final String description;
    private final int num_ratings;
    private final int average_rating;

    private final int imagesURL;
    private final List <FoodItem> menu; //menu List
    private final FoodItem item1; //FoodItems in a menu List
    private final FoodItem item2;
    private final FoodItem item3;
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
        imagesURL= 0;
        menu= null;
        item1= null;
        item2= null;
        item3= null;
        num_menu_items=0;
        location= null;
    }

    public Restaurant(final String newID, final String newRestaurantName, final String newCategory, final String description, final int num_ratings, final int average_rating, final int imagesURL, final FoodItem item1, final FoodItem item2, final FoodItem item3, final int num_menu_items, final String location )
    {
        this.menu =  new ArrayList<>();

        restaurantID = newID;
        restaurantName = newRestaurantName;
        this.newCategory= newCategory;
        this.description= description;
        this.num_ratings= num_ratings;
        this.average_rating= average_rating;
        this.imagesURL= imagesURL;
        this.item1= item1;
        this.item2= item2;
        this.item3= item3;

        this.num_menu_items=num_menu_items;
        this.location= location;

        //add the FoodItems to menu List
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);

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
        return (null);
    }

    public List<FoodItem> getMenuItems(){
        return(menu);
    }
    public FoodItem getItem1(){
        return item1;
    }
    public FoodItem getItem2(){
        return item2;
    }
    public FoodItem getItem3() {
        return item3;
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

    public int getImagesURL() {
        return imagesURL;
    }

}