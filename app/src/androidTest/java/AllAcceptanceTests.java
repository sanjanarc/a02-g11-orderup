
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import SystemTests.AddCommentTest;
import SystemTests.AddUserTest;
import SystemTests.LoginTest;
import SystemTests.SearchRestaurantTest;


/*
    Purpose: This class tests every acceptance test
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        LoginTest.class,
        AddUserTest.class,
        SearchRestaurantTest.class,
        AddCommentTest.class

})
public class AllAcceptanceTests {
}