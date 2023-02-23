package com.example.orderup.logic;

import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.persistance.stub.RestaurantPersistenceStub;
import com.example.orderup.persistance.stub.UserPersistenceStub;

public class Services {

    private static UserPersistence userPersistence= null;
    private static RestaurantPersistence restaurantPersistence= null;

    public static synchronized UserPersistence getUserPersistence(){

        if(userPersistence== null){
            userPersistence= new UserPersistenceStub();
        }

        return userPersistence;
    }

    public static synchronized RestaurantPersistence getRestaurantPersistence() {

        if (restaurantPersistence == null) {
            restaurantPersistence = new RestaurantPersistenceStub();
        }

        return restaurantPersistence;
    }
}
