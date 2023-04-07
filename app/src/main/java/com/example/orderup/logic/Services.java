package com.example.orderup.logic;

import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.persistance.hsqldb.RestaurantPersistenceHSQLDB;
import com.example.orderup.persistance.hsqldb.UserPersistenceHSQLDB;

//Class that holds most the static variables.
public class Services
{
    private static String currentUser = null;
    private static String dbPath = "DB";
    private static UserPersistence userPersistence= null;
    private static RestaurantPersistence restaurantPersistence= null;

    //Get the User database setup and ready to use.
    public static synchronized UserPersistence getUserPersistence()
    {
        if(userPersistence== null)
        {
            userPersistence = new UserPersistenceHSQLDB(getDBPathName());
        }

        return userPersistence;
    }

    //Get the Restaurant database setup and ready for use.
    protected static synchronized RestaurantPersistence getRestaurantPersistence()
    {
        if (restaurantPersistence == null)
        {
            restaurantPersistence = new RestaurantPersistenceHSQLDB(getDBPathName());
        }

        return restaurantPersistence;
    }

    //Tell the system which account is using the system.
    public static void setCurrentUser(String email)
    {
        currentUser = email;
    }

    //Return the current account email.
    public static String getCurrentUser()
    {
        return currentUser;
    }

    public static void setDBPathName(final String name)
    {
        try
        {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        dbPath = name;
    }

    //Return the database path.
    public static String getDBPathName()
    {
        return dbPath;
    }
}