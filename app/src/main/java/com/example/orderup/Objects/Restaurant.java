package com.example.orderup.Objects;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Restaurant {

    private final int restaurantID;
    private final String restaurantName;
    private final String cityName;
    private final String newCategory;
    private final String description;
    //private int num_ratings = 0;
    //private int average_rating = 0;

    private  int imagesURL=0;

    //private final String imagesURL;
    private final List <FoodItem> menu; //menu List
    private final FoodItem item1; //FoodItems in a menu List
    private final FoodItem item2;
    private final FoodItem item3;
    private  int num_menu_items;
    //private ArrayList<String> location'
    private final String location;


    public Restaurant(final int newID)
    {
        restaurantID = newID;
        restaurantName = null;
        newCategory= null;
        cityName=null;
        description= null;
        //num_ratings= 0;
        //average_rating= 0;
        //imagesURL= null;
        menu= null;
        item1= null;
        item2= null;
        item3= null;
        num_menu_items=0;
        location= null;
    }

    public Restaurant(int newID, String newRestaurantName, String newCategory, String cityName, String description, final FoodItem item1, final FoodItem item2, final FoodItem item3,final int num_menu_items, final String location )
    {
        this.menu =  new ArrayList<>();

        restaurantID = newID;
        restaurantName = newRestaurantName;
        this.newCategory= newCategory;
        this.cityName= cityName;
        this.description= description;
        //this.num_ratings= 0;
        //this.average_rating= 0;
        //this.imagesURL= imagesURL;
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

    public Restaurant(int newID, String newRestaurantName, String newCategory, String cityName, String description, final FoodItem item1, final FoodItem item2, final FoodItem item3, final String location )
    {
        this.menu =  new ArrayList<>();

        restaurantID = newID;
        restaurantName = newRestaurantName;
        this.newCategory= newCategory;
        this.cityName= cityName;
        this.description= description;
//        this.num_ratings= num_ratings;
//        this.average_rating= average_rating;
        //this.imagesURL= imagesURL;
        this.item1= item1;
        this.item2= item2;
        this.item3= item3;

        this.num_menu_items=0;
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


    //access the food's image from foodItem object class
    /*public String getFood_imageURL(){
        return (null);
    }
     */

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

    public int getRestaurantID()
    {
        return (restaurantID);
    }

    public String getRestaurantName()
    {
        return (restaurantName);
    }

    public String toString()
    {
        return String.format("Restaurant id: %d, Restaurant name: %s", restaurantID, restaurantName);
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