package com.example.orderup;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.orderup.Persistence.RestaurantPersistenceStubTest;
import com.example.orderup.persistance.hsqldb.RestaurantPersistenceHSQLDBTest;
import com.example.orderup.persistance.hsqldb.UserPersistenceHSQLDBTest;
import com.example.orderup.Objects.FoodItemTest;
import com.example.orderup.Objects.GiftcardTest;
import com.example.orderup.Objects.RestaurantTest;
import com.example.orderup.Objects.UserTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({

        RestaurantPersistenceStubTest.class,
        RestaurantPersistenceHSQLDBTest.class,
        UserPersistenceHSQLDBTest.class,
        FoodItemTest.class,
        GiftcardTest.class,
        RestaurantTest.class,
        UserTest.class

})
public class AllTests
{
}
