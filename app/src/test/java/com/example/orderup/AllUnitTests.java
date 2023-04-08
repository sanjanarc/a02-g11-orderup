package com.example.orderup;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.orderup.persistance.hsqldb.RestaurantPersistenceHSQLDBTest;
import com.example.orderup.persistance.hsqldb.UserPersistenceHSQLDBTest;
import com.example.orderup.Objects.FoodItemTest;
import com.example.orderup.Objects.GiftcardTest;
import com.example.orderup.Objects.RestaurantTest;
import com.example.orderup.Objects.UserTest;
import com.example.orderup.logic.RestaurantServicesTest;
import com.example.orderup.logic.UserServicesTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({


        RestaurantPersistenceHSQLDBTest.class,
        UserPersistenceHSQLDBTest.class,
        FoodItemTest.class,
        GiftcardTest.class,
        RestaurantTest.class,
        UserTest.class,
        RestaurantServicesTest.class,
        UserServicesTest.class


})
public class AllUnitTests
{
}
