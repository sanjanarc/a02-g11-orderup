package com.example.orderup.Persistence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.List;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.Restaurant;
/*
import com.example.orderup.persistance.stub.RestaurantPersistenceStub;

public class RestaurantPersistenceStubTest {
    private RestaurantPersistenceStub restaurantPersistence;

    @Before
    public void testRestPersistenceStub(){
        restaurantPersistence= new RestaurantPersistenceStub();
    }

    create Restaurant in method and check if insertion in the database was successfull

    @Test
    public void testRestaurantInsertion(){

        Restaurant rest= new Restaurant(2, "Baked Expectations", "Pie,Dessert,Sweet,Cake,Cookies,Cupcakes", "Winnipeg", "", new FoodItem(2,1, "Fresh Strawberry Cheesecake",10.00, "https://cdn.doordash.com/media/photos/42984140-591d-4fd4-a326-5c1711c50564-retina-large-jpeg","Delicious, juicy, plump strawberries heaped atop a creamy plain cheesecake."), new FoodItem(2,2, "Cherry Royale Cheesecake", 9.75, "https://cdn.doordash.com/media/photos/0e61f405-b144-46d2-87c9-baf68c57d9ec-retina-large-jpeg", "The classic, plain (if you can call it that!) cheesecake with tons of dark cherry topping."),new FoodItem(2,3, "Oreo Cookie Cheesecake", 9.75,"https://cdn.doordash.com/media/photos/0ff3f07d-1fd8-49e7-9868-f4d95c9224dd-retina-large-jpeg", "What can we say – everyone loves it. Chocolate cookie crust, Oreos generously mixed through the cheesecake – topped with more cookies and cream." ), 3, "161 Osborne St Winnipeg MB R3L 1Y7","image" );
        Assert.assertTrue("Restaurant added to stub, should be a successfully insertion(True):",restaurantPersistence.insertRestaurant(rest));
    }

    confirm existing restaurants in the stub

    @Test
    public void testFindRestaurant1(){
        Assert.assertTrue("Restaurant 1 is in the stub, confirmation should be successfull(True):",restaurantPersistence.findRestaurant(1));
    }
    @Test
    public void testFindRestaurant3(){
        Assert.assertTrue("Restaurant 3 is in the stub, confirmation should be successful(True):",restaurantPersistence.findRestaurant(3));
    }

    //confirm restaurant 4 is not in the list

    @Test
    public void testFindRestaurant4(){
        boolean restNotFound= !(restaurantPersistence.findRestaurant(4));
        Assert.assertTrue("Restaurant 4 is not the stub, confirmation should be successful(True):",restNotFound);
    }

    //Confirm size of the list: to check the number of current restaurants

    @Test
    public void testListSize(){
        List<Restaurant> restInList= restaurantPersistence.getRestaurantSequential();
        int sizeList= restInList.size();
        Assert.assertEquals("There are 2 restaurants in the list, confirmation should be successful(True):",2,sizeList);
    }


    //delete restaurant inserted in method testRestaurantInsertion() and check if restaurant deletion was successfull

    @Test
    public void testRestaurantDeletion(){
        restaurantPersistence.deleteRestaurant(2); //delete restaurant
        boolean restNotFound= !(restaurantPersistence.findRestaurant(2)); //find restaurant is in the stub, if not found->success
        Assert.assertTrue("Restaurant deleted from stub,should be a successfully deletion(True):",restNotFound);
    }


}

 */
