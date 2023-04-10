package SystemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringContains.containsString;

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
        Purpose: This class tests changing a User's address

 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddUserAddress {

    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivity = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Test
    public void addUserAdd()
    {
        //enter user's email and password
        onView(withId(R.id.emailInput)).perform(typeText("admin2@email.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("admin123"));

        //click on sign in
        onView(withId(R.id.signInButton)).perform(click());
        closeSoftKeyboard();

        //go to User account
        onView(withId(R.id.user_account)).perform(click()); //click the user account fragment

        //Add User address
        onView(withId(R.id.addAddressButton)).perform(click());
        onView(withId(R.id.streetInput)).perform(typeText("1568 Pembina Hwy"));
        onView(withId(R.id.cityInput)).perform(typeText("Winnipeg"));
        onView(withId(R.id.provinceInput)).perform(typeText("Manitoba"));
        onView(withId(R.id.postalInput)).perform(typeText("R2M5C3"));
        onView(withText("Done")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click()); // Perform the click action on "OK"
        closeSoftKeyboard();

        //CONFIRM address was added.
        onView(withId(R.id.infoContainer)).check(matches(withText((containsString("R2M5C3")))));

    }
}
