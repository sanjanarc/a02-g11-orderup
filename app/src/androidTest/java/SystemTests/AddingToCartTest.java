package SystemTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.matcher.ViewMatchers;

import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.contrib.RecyclerViewActions.*;

import static androidx.test.espresso.action.ViewActions.scrollTo;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.ViewInteraction;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;


import static androidx.test.espresso.matcher.ViewMatchers.withParent;


import static org.hamcrest.CoreMatchers.*;



import android.view.View;
import android.view.ViewGroup;


import static org.hamcrest.core.StringContains.containsString;

import android.view.KeyEvent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.orderup.R;
import com.example.orderup.presentation.LoginActivity;
import com.example.orderup.presentation.RegisterActivity;
import com.example.orderup.presentation.HomeFragment;
import com.example.orderup.presentation.MenuAdapter;



/*
        Purpose: This class tests adding a food item to Cart
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddingToCartTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivity = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Test
    public void addToCart(){

        //login to account
        onView(withId(R.id.emailInput)).perform(typeText("admin2@email.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("admin123"));
        onView(withId(R.id.signInButton)).perform(click());

        //search for a specified restaurant
        //search for a restaurant keyword in the search bar and press Enter
        onView(withId(R.id.searchView)).perform(typeText("Korean Garden"),pressKey(KeyEvent.KEYCODE_ENTER));
        //click the restaurant
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //add the second menu item to cart
        onView(withTagValue(equalTo("Submit1"))).perform(click());
        onView(withText("Ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click()); // Perform the click action on "OK"
        onView(withId(R.id.ViewCartButtonXML)).perform(click());

        //Confirm by removing item from card
        onView(withId(R.id.Submit)).perform((click()));










    }


}
