package com.example.orderup.logic;


import android.util.Log;

import java.util.ArrayList;


import java.util.List;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.persistance.stub.RestaurantPersistenceStub;


public class Search_algorithm {

    String userInput;

    public Search_algorithm(String userInput) {
        this.userInput = userInput;
    }

    //------------------------------------------------------
    // searchRestaurant
    //
    // PURPOSE:   The method implements a search algorithm. It searches for a restaurant or a restaurant's category that matches user's input.
    //            It calls a searchRestaurantKey function to get the index of each restaurant recommended from the stub restaurant database.
    //
    // PARAMETERS:
    //            String user_input: user input
    //
    // Returns:
    //           ArrayList<Restaurant>: an ArrayList<Restaurant> of  restaurant recommendation based on user's search
    //------------------------------------------------------
    public static List<Restaurant>  searchRestaurant(String userInput) {

        List<Restaurant> restaurants = RestaurantServices.getRestList(); // access restaurants from script

        String[] restNames = new String[restaurants.size()];
        String[] restCategories = new String[restaurants.size()];

        // need to link this to restaurant array list created
        // for restaurant name in array list add to restaurants array
        for (int i = 0; i < restaurants.size(); i++) {
            String name = restaurants.get(i).getRestaurantName();      // want to use method for restaurant object
            String category = restaurants.get(i).getRestaurantCategory();

            restNames[i] = name;
            restCategories[i] =  category;

        }

        List<Restaurant> restaurantResults = new ArrayList<Restaurant>();



        List<Integer> results= searchRestaurantKey(errorCheck(userInput), restNames, restCategories); //korean, sushi, nagiri, apple
        //Log.d("error checked user input",errorCheck(userInput));
        if(results.isEmpty()){
            System.out.println("Your search does not match any restaurants on OrderUp");
        }else{
            for (int j = 0; j < results.size(); j++) {
                restaurantResults.add(restaurants.get(results.get(j)));
            }

        }
        return restaurantResults;

    }




    //error check
    public static String errorCheck(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return str;
    }

    //------------------------------------------------------
    // searchRestaurantKey
    //
    // PURPOSE:   The method implements a search algorithm. Its searches for a restaurant or a restaurant's category that matches user's input.
    //
    // PARAMETERS:
    //            String user_input: user input
    //            String[] restaurants: an array of restaurant names
    //            String[] categories: an array of category names
    // Returns:
    //           ArrayList<Integer>: an ArrayList<Integer> of relevant restaurant's row number
    //------------------------------------------------------
    public static List<Integer> searchRestaurantKey( String user_input, String[] restaurants, String[] categories){
        ArrayList<Integer> restaurantFound= new ArrayList<Integer>(); //an array list of restaurant's row number relevant to the user input

        //start iteration from 1, as first row is Headings
        for(int i=1; i<categories.length; i++ ){ //iterating through the category and restaurants list to find match for "user input"
            if( errorCheck(categories[i]).contains(user_input) || errorCheck(restaurants[i]).contains(user_input)){
                //Log.d("error checked restaurant names",errorCheck(restaurants[i]));
                restaurantFound.add(i);
            }
        }
        return restaurantFound;
    }
}


