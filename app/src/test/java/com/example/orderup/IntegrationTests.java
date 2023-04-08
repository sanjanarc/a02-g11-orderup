package com.example.orderup;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.example.orderup.persistance.hsqldb.IntegrationTests.RestaurantIntegrationTest;
import com.example.orderup.persistance.hsqldb.IntegrationTests.UserIntegrationTest;



@RunWith(Suite.class)
@Suite.SuiteClasses({
        //all integration test classes
        RestaurantIntegrationTest.class,
        UserIntegrationTest.class

})

public class IntegrationTests {
}
