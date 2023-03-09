package com.example.orderup.logic;

import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.persistance.hsqldb.UserPersistenceHSQLDB;
import com.example.orderup.persistance.stub.RestaurantPersistenceStub;

public class Services {
    //private static String dbName = "UserDB";
    private static String currentUser = null;
    private static UserPersistence userPersistence= null;
    private static RestaurantPersistence restaurantPersistence= null;

    private static String dbPath;

    public static synchronized UserPersistence getUserPersistence(){
        //setDBPathName(dbName);
        if(userPersistence== null){
            //userPersistence= new UserPersistenceStub();
            userPersistence = new UserPersistenceHSQLDB(getDBPathName());
        }

        return userPersistence;
    }

    public static synchronized UserPersistence getUserPersistenceDB() {
        //setDBPathName(dbName);
        if(userPersistence== null){
            userPersistence = new UserPersistenceHSQLDB(getDBPathName());
        }
        return userPersistence;
    }

    public static synchronized RestaurantPersistence getRestaurantPersistence() {

        if (restaurantPersistence == null) {
            restaurantPersistence = new RestaurantPersistenceStub();
        }

        return restaurantPersistence;
    }

    public static void setCurrentUser(String email){
        currentUser = email;
    }

    public static String getCurrentUser(){
        return currentUser;
    }

    public static void setDBPathName(final String name)
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        //dbPath = name;
    }

    public static String getDBPathName()
    {
        return dbPath;
    }
}
