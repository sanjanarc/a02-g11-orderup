package com.example.orderup;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.orderup.Objects.RestaurantObjectTest;
import com.example.orderup.Persistence.RestaurantPersistenceStubTest;
import com.example.orderup.persistance.hsqldb.RestaurantPersistenceHSQLDBTest;
import com.example.orderup.persistance.hsqldb.UserPersistenceHSQLDBTest;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        RestaurantObjectTest.class,
        RestaurantPersistenceStubTest.class,
        RestaurantPersistenceHSQLDBTest.class,
        UserPersistenceHSQLDBTest.class

})
public class AllTests
{

}
