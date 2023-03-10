package com.example.orderup.logic;


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
    //           ArrayList<String>: an ArrayList<String> of  restaurant recommendation based on user's search
    //------------------------------------------------------
    public static ArrayList<String>  searchRestaurant(String userInput) {

        RestaurantPersistence p = new RestaurantPersistenceStub();
        List<Restaurant> restaurants = p.getRestaurantSequential(); // want to use method from restaurant interface

        String[] restNames = new String[50];
        String[] restCategories = new String[50];

        // need to link this to restaurant array list created
        // for restaurant name in array list add to restaurants array
        for (int i = 0; i < restaurants.size(); i++) {
            String name = restaurants.get(i).getRestaurantName();      // want to use method for restaurant object
            String category = restaurants.get(i).getRestaurantCategory();

            restNames[i] = name;
            restCategories[i] =  category;

        }

        ArrayList<String> restaurantResults = new ArrayList<String>();



        ArrayList<Integer> results= searchRestaurantKey(errorCheck(userInput), restNames, restCategories); //korean, sushi, nagiri, apple
        if(results.isEmpty()){
            System.out.println("Your search does not match any restaurants on OrderUp");
        }else{
            for (int j = 0; j < results.size(); j++) {
                restaurantResults.add(restNames[results.get(j)]);
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
    public static ArrayList<Integer> searchRestaurantKey( String user_input, String[] restaurants, String[] categories){
        ArrayList<Integer> restaurantFound= new ArrayList<Integer>(); //an array list of restaurant's row number relevant to the user input

        //start iteration from 1, as first row is Headings
        for(int i=1; i<categories.length; i++ ){ //iterating through the category and restaurants list to find match for "user input"
            if( errorCheck(categories[i]).contains(user_input) || errorCheck(restaurants[i]).contains(user_input)){
                restaurantFound.add(i);
            }
        }
        return restaurantFound;
    }
}

