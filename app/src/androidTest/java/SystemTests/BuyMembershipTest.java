package SystemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.Matchers.not;


import android.view.KeyEvent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.orderup.presentation.LoginActivity;
import com.example.orderup.presentation.HomeFragment;
import com.example.orderup.presentation.UserAccountFragment;
import com.example.orderup.R;

/*
        Purpose: This class tests purchasing a Membership

 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BuyMembershipTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivity = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Test
    public void purchaseMembership(){
        //enter user's email and password
        onView(withId(R.id.emailInput)).perform(typeText("admin2@email.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("admin123"));

        //click on sign in
        onView(withId(R.id.signInButton)).perform(click());
        closeSoftKeyboard();

        //go to User account
        onView(withId(R.id.user_account)).perform(click()); //click the user account fragment

        //click Buy membership
        onView(withId(R.id.membershipButton)).perform(click());
        //enter yes to purchase
        onView(withId(R.id.confirmationInput)).perform(typeText("Yes"));
        //click done
        onView(withId(R.id.confirmationInput)).perform(pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.confirmationInput)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

        closeSoftKeyboard();
        //Membership is enabled if button can not be clicked anymore
        onView(withId(R.id.membershipButton)).check(matches(not(isClickable())));
    }

}
