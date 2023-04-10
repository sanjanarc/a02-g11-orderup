
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import SystemTests.AddCommentTest;
import SystemTests.AddCreditCard;
import SystemTests.AddUserAddress;
import SystemTests.AddUserTest;
import SystemTests.AddingToCartTest;
import SystemTests.LoginTest;
import SystemTests.SearchRestaurantTest;
import SystemTests.BuyMembershipTest;


/*
    Purpose: This class tests every acceptance test
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        LoginTest.class,
        AddUserTest.class,
        SearchRestaurantTest.class,
        AddCommentTest.class,
        BuyMembershipTest.class,
        AddCreditCard.class,
        AddUserAddress.class,
        AddingToCartTest.class

})
public class AllAcceptanceTests {
}