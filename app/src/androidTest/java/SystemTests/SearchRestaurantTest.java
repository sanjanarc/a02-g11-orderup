package SystemTests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import android.view.KeyEvent;

import android.view.View;
import android.view.ViewGroup;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.orderup.R;
import com.example.orderup.presentation.LoginActivity;

/*
        Purpose: This class tests searching for a Restaurant in the search bar on the Home page after Logging in
        Test: Search either a restaurant or a cuisine
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchRestaurantTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivity = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Test
    public void searchRestaurant(){

        onView(withId(R.id.emailInput)).perform(typeText("admin2@email.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("admin123"));

        //click on sign in
        onView(withId(R.id.signInButton)).perform(click());

        //search for a restaurant keyword in the search bar and press Enter
        onView(withId(R.id.searchView)).perform(typeText("Korean Garden"),pressKey(KeyEvent.KEYCODE_ENTER));
        //confirm at lease one search result is displayed
        onView(withId(R.id.searchLayout)).check(matches(minRestaurantDisplay(1)));

        //clear search before next test
        for(int i=0; i<"Korean Garden".length(); i++){
            onView(withId(R.id.searchView)).perform(pressKey(KeyEvent.KEYCODE_DEL));
        }
        //search for a cuisine in the search bar and press Enter
        onView(withId(R.id.searchView)).perform(typeText("Korean"),pressKey(KeyEvent.KEYCODE_ENTER));
        //confirm at lease one search result is displayed
        onView(withId(R.id.searchLayout)).check(matches(minRestaurantDisplay(1)));

        //clear search before next test
        for(int i=0; i<"Korean".length(); i++){
            onView(withId(R.id.searchView)).perform(pressKey(KeyEvent.KEYCODE_DEL));
        }
        //search with an empty search bar
        onView(withId(R.id.searchView)).perform(typeText(""),pressKey(KeyEvent.KEYCODE_ENTER));
        //expected result =0
        onView(withId(R.id.searchLayout)).check(matches(minRestaurantDisplay(0)));

    }

    /*
        minRestaurantDisplay tests that at least one search result is displayed on the screen
     */
    public static Matcher minRestaurantDisplay(int count) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                return view instanceof ViewGroup && ((ViewGroup) view).getChildCount() >= count;
            }
            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
