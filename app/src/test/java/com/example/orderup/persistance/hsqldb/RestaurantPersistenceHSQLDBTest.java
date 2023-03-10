package com.example.orderup.persistance.hsqldb;

import com.example.orderup.logic.Services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.persistance.hsqldb.PersistenceException;
import com.example.orderup.persistance.hsqldb.RestaurantPersistenceHSQLDB;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;



public class RestaurantPersistenceHSQLDBTest {

    @Test
    public void testRestaurantInsertion() {
        RestaurantPersistence restaurantPersistence = new RestaurantPersistenceHSQLDB(Services.getDBPathName());
        Assert.assertNotNull("Successful datapath established.", restaurantPersistence);
        //Assert.assertTrue("Restaurant added to stub, should be a successfully insertion(True):"));
    }
}
