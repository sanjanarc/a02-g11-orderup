package com.example.orderup.persistance.stub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.Objects.FoodItem;

public class RestaurantPersistenceStub implements RestaurantPersistence {
    private List<Restaurant> restaurants;


    public RestaurantPersistenceStub() {
        this.restaurants = new ArrayList<>();

        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));
        restaurants.add(new Restaurant("Restaurant 1", "Indian Food Corner","Tandoori,Indian, Chicken", "Traditional Indian dishes from butter chicken to chicken biryani.", 0, 0, "", new FoodItem("IFC Spl Saag & Makki Di Roti Meal", 9.99, "https://cdn.doordash.com/media/photos/ee8a25ab-0457-4410-993d-670d73bffb42-retina-large-jpeg", "Saag and two makki di rotis with salted lassi."), new FoodItem("Vegetarian Combo", 12.99, "https://cdn.doordash.com/media/photos/bb5dfdcd-8ea0-4a4e-b89d-dfc7071873dd-retina-large-jpeg", "Chana masala with one of your choice of Vegetarian curry or sabzi, rice, Butter naan bread and pickle."),new FoodItem("Pani Puri", 6.99, "https://cdn.doordash.com/media/photos/8b253848-5857-413c-a9bb-a9d3b8d66262-retina-large-jpeg", "Round, hollow puri fried and filled with a mixture of flavored water, tamarind chutney, chilly, chat masala, potatoes, and onions"), 3, null));

        /*
        restaurants.add(new Restaurant("Restaurant 2", "Baked Expectations", "Pie,Dessert,Sweet,Cake,Cookies,Cupcakes", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 3", "Banh Mi King", "Vietnamese,Savory,Sandwiches,Phom Poutine", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 4", "Jollibee", "Fried Chicken,Philippines,Filipino Fast Food", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 5", "JOEY Restaurants", "Burgers,Canadian,Pasta,Sandwiches,Fries", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 6", "Mar's Sisig", "Filipino,Philippines,Pho", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 7", "Aroma Bistro", "KWB,Chinese,Hong Kong,Sichuan,Savory,China", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 8", "LOCAL Public Eatery", "Burgers,Neighbourhood,Drinks,Bar", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 9", "Earls Kitchen + Bar", "Pizza,Canadian,Drinks,Burgers,Pasta", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 10", "Korean Garden", "Korean", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 11", "Nuburger", "Burgers,Fast Food", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 12", "Wendy's", "Burgers,Fast Food, Milk Shakes", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 13", "Popeyes Louisiana Kitchen", "Halal,Chicken,Fast Food", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 14", "PC Express™ Rapid Delivery", "Convenience,Fast,Superstore,No Frills", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 15", "Wako Sushi Cafe", "Sushi,Japanese", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 16", "Logan Corner Restaurant", "Chicken,Chinese,China", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 17", "Happy Lemon", "Bubble Tea, Drinks", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 18", "Little Viet", "Bubble Tea,Vietnamese", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 19", "Mama Nors Kitchen", "Filipino,Philippines,Catering", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 20", "Poke Mono", "Poke,Hawaiian,Bowls,Chicken", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 21", "Leopold's Tavern", "Burgers,Pub,Drinks,Fries", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 22", "Harvey's", "Burgers,Fast Food", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 23", "Tim Hortons", "Coffee,Donuts,Fast Food,Drinks,Sandwiches,Canadian", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 24", "Naru Sushi Japanese Restaurant", "Sushi,Japanese", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 25", "Stella's", "Breakfast,Bakery,Cafe,Brunch", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 26", "Lamar Donair & Burgers", "Wraps,Shwarma, Falafel, Middle Eastern", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 27", "Magic Sushi & Wok", "Sushi,Japanese", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 28", "Honu Poke", "Poke, Hawaiian", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 29", "Thida's Thai Restaurant", "Thai", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 30", "Burrito del Rio", "Burritos, South American, Mexican", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 31", "St. James Burger and Chip", "Burgers,Fries", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 32", "Flying Pizza", "Pizza,Italian", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 33", "Dim Sum Kingdom Restaurant", "Chinese,Dim Sum,Soup", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 34", "Hao Hao Chinese Restaurant", "Chinese", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 35", "Junior's Restaurant", "Burgers,Fast Food", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 36", "Gongcha", "Bubble Tea, Drinks", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 37", "The Cheesecake Factory", "Cheesecake,Dessert,Comfort Food", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 38", "McDonald's", "Burgers,Fast Food", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 39", "Juvian's Restaurant", "Filipino,Philippines,Sisig,Kare-Kare,Adobo", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 40", "Les Saj Restaurant", "Lebanese,Middle Eastern,Shawarma,Falafel", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 41", "Sushi Hon", "Sushi,Japanese", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 42", "Little Korea", "Korean", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 43", "Smoke's Poutinerie", "Poutine,Canadian", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 44", "Kawaii Crepe", "Crepe,Japanese", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 45", "Cilantro's Modern Indian Cuisine", "Indian,Modern, Curry", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 46", "Sargent Taco Shop", "Mexican,Tacos,Burritos", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 47", "Siam Authentic Thai Cuisine", "Thai,Curry,Pad Thai", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 48", "Clay Oven", "Indian,Tandoori, Curry", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 49", "Kimbaek Restaurant", "Korean", "", 0, 0, "","", 0,""));
        restaurants.add(new Restaurant("Restaurant 50", "New York Fries", "Fries,Hot Dogs,Poutine,Canadian", "", 0, 0, "","", 0,""));
        */
    }

    @Override
    public List<Restaurant> getRestaurantSequential() {
        return Collections.unmodifiableList(restaurants); //not using collection
    }

    @Override
    public List<Restaurant> getRestaurantRandom(Restaurant currentRestaurant) {
        List<Restaurant> newRestaurants = new ArrayList<>();
        int index;

        index = restaurants.indexOf(currentRestaurant);
        if (index >= 0)
        {
            newRestaurants.add(restaurants.get(index));
        }
        return newRestaurants;
    }

    @Override
    public Restaurant insertRestaurant(Restaurant currentRest) {
        // don't bother checking for duplicates
        restaurants.add(currentRest);
        return currentRest;
    }

    @Override
    public Restaurant updateRestaurant(Restaurant currentRest) {
        int index;

        index = restaurants.indexOf(currentRest);
        if (index >= 0)
        {
            restaurants.set(index, currentRest);
        }
        return currentRest;
    }

    @Override
    public void deleteRestaurant(Restaurant currentRest) {
        int index;

        index = restaurants.indexOf(currentRest);
        if (index >= 0)
        {
            restaurants.remove(index);
        }
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}