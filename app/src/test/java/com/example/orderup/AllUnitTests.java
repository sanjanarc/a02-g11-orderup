package com.example.orderup;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.orderup.persistance.hsqldb.RestaurantPersistenceHSQLDBTest;
import com.example.orderup.persistance.hsqldb.UserPersistenceHSQLDBTest;
import com.example.orderup.Objects.FoodItemTest;
import com.example.orderup.Objects.GiftcardTest;
import com.example.orderup.Objects.RestaurantTest;
import com.example.orderup.Objects.UserTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({

        RestaurantPersistenceHSQLDBTest.class,
        UserPersistenceHSQLDBTest.class,
        FoodItemTest.class,
        GiftcardTest.class,
        RestaurantTest.class,
        UserTest.class,


})
public class AllUnitTests
{
}
