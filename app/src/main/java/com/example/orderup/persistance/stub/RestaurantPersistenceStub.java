package com.example.orderup.persistance.stub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.R;
import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.Objects.FoodItem;

public class RestaurantPersistenceStub implements RestaurantPersistence {
    private List<Restaurant> restaurants;


    public RestaurantPersistenceStub() {
        this.restaurants = new ArrayList<>();

        restaurants.add(new Restaurant(1, "Indian Food Corner","Tandoori,Indian, Chicken", "Winnipeg","Traditional Indian dishes from butter chicken to chicken biryani.",new FoodItem(1,1, "IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem(1,2,"Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem(1,3,"Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant(3,"Banh Mi King","Vietnamese,Savory,Sandwiches,Phom Poutine","Winnipeg","Choose from an incredible selection of appetizing rice dishes, Vietnamese salads, pho, Viet subs, baoger and more.",new FoodItem(3,1, "Pork Belly Baoger",7.79,"https://cdn.doordash.com/media/photos/3ccb98e4-3e03-411b-ae5d-4c7487db481f-retina-large.jpg","Slow-braised pork belly cooked in a hot oven and served on a steamed bun with cheese, pickled daikons, red onions, and lettuce."),new FoodItem(3,2,"Beef Baoger",7.99,"https://cdn.doordash.com/media/photos/ffa52860-4211-47aa-bfdb-f72653cd313c-retina-large.jpg","Homemade beef patties, steamed bun, cheese, pickled daikons, red onions, and lettuce."),new FoodItem(3,3, "Deluxe Beef Pho",11.29,"https://cdn.doordash.com/media/photos/8a2be946-c47b-4055-a8ce-1d6f531a68f3-retina-large.jpg","Gluten friendly. Sliced top sirloin raw beef, beef brisket, and beef balls with your choice of broth served with a side of bean sprouts and basil."),3,"510 Portage Ave Winnipeg MB R3C 0G2"));


    }

    /*
    Inserts the passed restaurant to a restaurant list and checks if the restaurant has been inserted successfully
     */
    public boolean insertRestaurant(Restaurant currentRest) {
        boolean insertSuccess=false;

        if(currentRest!=null) {
            restaurants.add(currentRest);
        }
        //check if the restaurant has been inserted successfully
        if(findRestaurant(currentRest.getRestaurantID())){

            insertSuccess=true;

        }

        return insertSuccess;
    }
    /*
    method called in insertRestaurant
    searches for restaurantID in the list of restaurants
     */
    public boolean findRestaurant(int checkID){
        boolean restaurantFound=false;

        for(Restaurant rest : restaurants){
            if(rest.getRestaurantID()==checkID && !restaurantFound){
                restaurantFound=true;
            }
        }
        return restaurantFound;
    }

    /*
    Update restaurant with new restaurant passed in parameter
     */
    public void updateRestaurant(Restaurant currentRest) {
        int index;

        index = restaurants.indexOf(currentRest);
        if (index >= 0)
        {
            restaurants.set(index, currentRest);
        };
    }

    /*
    Delete restaurant with id passed in the parameter
     */
    public void deleteRestaurant(int restaurantNum) {
        boolean restFound=false;
        Restaurant restToDelete=null;

        if(restaurantNum>=0){
            for(Restaurant rest: restaurants){
                if(rest.getRestaurantID()==restaurantNum & !restFound){
                    restToDelete= rest;
                    restFound=true;
                }
            }
        }
        if(restToDelete!=null){
            restaurants.remove(restToDelete);
        }
    }

    /*
    return list of all current restaurants
     */
    public List<Restaurant> getRestaurantSequential() {
        return restaurants;
    }


}