package com.example.orderup.logic;

import java.util.ArrayList;
import java.util.List;

import com.example.orderup.Objects.Restaurant;

/**
 * This class will filter out the restaurant list based on the user input.
 */
public class Search_algorithm {

    String userInput;

    /**
     * Constructor.
     *
     * @param userInput the user input.
     */
    public Search_algorithm(String userInput) {
        this.userInput = userInput;
    }

    /**
     * The method implements a search algorithm. It searches for a restaurant or a restaurant's category that matches user's input.
     * It calls a searchRestaurantKey function to get the index of each restaurant recommended from the stub restaurant database.
     *
     * @param userInput the user input data.
     * @return an ArrayList<Restaurant> of  restaurant recommendation based on user's search
     */
    public List<Restaurant> searchRestaurant(String userInput) throws Exception {

        RestaurantServices restaurantServices = new RestaurantServices(Services.getRestaurantPersistence());
        List<Restaurant> restaurants = restaurantServices.getRestList(); // access restaurants from script

        String[] restNames = new String[restaurants.size()];
        String[] restCategories = new String[restaurants.size()];

        // need to link this to restaurant array list created
        // for restaurant name in array list add to restaurants array
        for (int i = 0; i < restaurants.size(); i++) {
            String name = restaurants.get(i).getRestaurantName();      // want to use method for restaurant object
            String category = restaurants.get(i).getRestaurantCategory();

            restNames[i] = name;
            restCategories[i] = category;
        }

        List<Restaurant> restaurantResults = new ArrayList<>();
        List<Integer> results = searchRestaurantKey(reFormat(userInput), restNames, restCategories); //korean, sushi, nagiri, apple

        // Check if the restaurant exists or not.
        if (results.isEmpty()) {

            throw new MyException.EXCEPTION_ITEM_DOES_NOT_EXIST();

        } else {

            for (int j = 0; j < results.size(); j++) {

                restaurantResults.add(restaurants.get(results.get(j)));

            }
        }

        return restaurantResults;
    }

    /**
     * Re-format the giving string.
     *
     * @param str a string variable.
     * @return a string variable with all lower case and not extra symbol.
     */
    public String reFormat(String str) {
        str = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return str;
    }

    /**
     * The method implements a search algorithm. Its searches for a restaurant or a restaurant's category that matches user's input.
     *
     * @param user_input  the user input.
     * @param restaurants an array of restaurant names
     * @param categories  an array of category names
     * @return an ArrayList of relevant restaurant's row number
     */
    public List<Integer> searchRestaurantKey(String user_input, String[] restaurants, String[] categories) {

        ArrayList<Integer> restaurantFound = new ArrayList<>(); //an array list of restaurant's row number relevant to the user input

        //start iteration from 1, as first row is Headings
        for (int i = 0; i < categories.length; i++) { //iterating through the category and restaurants list to find match for "user input"

            if (reFormat(categories[i]).contains(user_input) || reFormat(restaurants[i]).contains(user_input)) {

                restaurantFound.add(i);

            }
        }

        return restaurantFound;
    }
}