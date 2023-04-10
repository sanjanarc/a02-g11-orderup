package com.example.orderup.logic;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;

import java.util.List;

/**
 * This class provided restaurant services to presentation layer.
 */
public class RestaurantServices {

    private final RestaurantPersistence restaurantPersistence;
    private Restaurant restaurant;
    private List<Restaurant> restaurants;
    private int currentRestaurant;

    /**
     * Constructor.
     *
     * @param restaurantPersistence get the restaurant persistence from input parameter.
     */
    public RestaurantServices(RestaurantPersistence restaurantPersistence) {
        this.restaurantPersistence = restaurantPersistence;
        restaurant=null;
        restaurants=null;
        currentRestaurant=0;
    }

    /**
     * Get the list of restaurant from database.
     *
     * @return list of restaurant object.
     */
    public List getRestList() {
        return restaurantPersistence.getRestaurantSequential();
    }
    //--------------------------------------------------------------------------------------------
    // Too lazy to write comment
    /**
     * method is used to retrieve restaurant one at a time from Restaurant List in RestaurantPersistence
     * Starting from the first restaurant in the list, the method returns the next restaurant each time it is called
     * until end of list is reached.
     * Once, end of list is reached, currentRestaurant is set to 0 again.
     * @return Restaurant at int currentRestaurant position in the list of restaurants
     */
    public Restaurant getSequential(){
        String result=null;
        if(restaurants==null){
            restaurants= restaurantPersistence.getRestaurantSequential();
            currentRestaurant=0;
        }
        if(currentRestaurant<restaurants.size()){
            restaurant= (Restaurant) restaurants.get(currentRestaurant);
            currentRestaurant++;
        }else{
            restaurant=null;
            restaurant=null;
            currentRestaurant=0;
        }
        return restaurant;
    }
    //----------------------------------------------------------------------------------------------
    /**
     * Get the specific restaurant object.
     *
     * @param pos the restaurant id.
     * @return one restaurant object.
     */
    public Restaurant getRest(int pos) {
        return restaurantPersistence.getRest(pos);
    }

    /**
     * Insert new user comment to database and ask restaurant to retrieve the newest user comments.
     *
     * @param rest    the restaurant object.
     * @param comment the comment string.
     */
    public void insertComment(Restaurant rest, String comment) {
        restaurantPersistence.insertComment(rest.getRestaurantID(), comment);
        rest.updateComment();
    }

    /**
     * Restaurant call this method to get the newest user comments.
     *
     * @param restID the restaurant id.
     */
    public List getComments(int restID) {
        return restaurantPersistence.getComments(restID);
    }
}
