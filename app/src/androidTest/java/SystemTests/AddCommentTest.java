package SystemTests;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.orderup.R;
import com.example.orderup.presentation.LoginActivity;
import com.example.orderup.presentation.RestaurantActivity;

/*
    Purpose: Test adding a comment in a restaurant's comment section
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddCommentTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivity = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);
    //public ActivityScenarioRule<RestaurantActivity> restaurantActivity = new ActivityScenarioRule<RestaurantActivity>(RestaurantActivity.class);

    public void testComment(){

        //login
        onView(withId(R.id.emailInput)).perform(typeText("admin2@email.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("admin123"));

        //click on sign in
        onView(withId(R.id.signInButton)).perform(click());
       // onView(withId(R.id.))

    }

}


