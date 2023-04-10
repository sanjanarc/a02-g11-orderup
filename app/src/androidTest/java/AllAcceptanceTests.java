
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import SystemTests.AddCommentTest;
import SystemTests.AddCreditCard;
import SystemTests.AddGiftCard;
import SystemTests.AddUserAddress;
import SystemTests.AddUserTest;
import SystemTests.AddingToCartTest;
import SystemTests.CheckoutTest;
import SystemTests.DeliveryPickupTest;
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
        AddingToCartTest.class,
        AddGiftCard.class,
        CheckoutTest.class,
        DeliveryPickupTest.class

})
public class AllAcceptanceTests {
}