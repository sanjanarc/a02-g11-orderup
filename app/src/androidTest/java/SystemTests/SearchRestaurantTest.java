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
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import android.view.KeyEvent;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import androidx.fragment.app.testing.FragmentScenario;


import static org.hamcrest.CoreMatchers.anything;

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
import com.example.orderup.presentation.HomeFragment;

/*
        Purpose: This class tests searching for a Restaurant in the search bar on the Home page (HomeFragment)
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchRestaurantTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivity = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    //public FragmentScenarioRule<HomeFragment> fragmentScenarioRule = FragmentScenarioRule.<HomeFragment>newBuilder().setFragmentFactory(new MyFragmentFactory()).build();
    //public FragmentScenario<HomeFragment> homeFragment = FragmentScenario.launchInContainer(HomeFragment.class);

    @Test
    public void searchRestaurant(){

        onView(withId(R.id.emailInput)).perform(typeText("admin2@email.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("admin123"));

        //click on sign in
        onView(withId(R.id.signInButton)).perform(click());


        //search for a keyword in the search bar and press Enter
        onView(withId(R.id.searchView)).perform(typeText("Korean"),pressKey(KeyEvent.KEYCODE_ENTER));
        //confirm at lease one search result is displayed
        onView(withId(R.id.searchLayout)).check(matches(minRestaurantDisplay(1)));

        //complete the test by exiting
        closeSoftKeyboard();
        pressBack();
        pressBack();

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
