package SystemTests;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
import com.example.orderup.presentation.RegisterActivity;
import com.example.orderup.logic.MyException;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserVerification;

/*
        Purpose: This class tests Registering a User's account.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddUserTest {
    @Rule
    public ActivityScenarioRule<RegisterActivity> registerActivity = new ActivityScenarioRule<RegisterActivity>(RegisterActivity.class);

    @Test
    public void registrationTest(){

        //Register for an Account: First name, last name, email address, enter password, re-enter password
        onView(withId(R.id.firstNameInput)).perform(typeText("first"));
        onView(withId(R.id.lastNameInput)).perform(typeText("last"));
        onView(withId(R.id.emailInput)).perform(typeText("firstlast@email.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("firstLast1!"));
        onView(withId(R.id.rePasswordInput)).perform(typeText("firstLast1!"));

        onView(withId(R.id.rePasswordInput)).perform(pressImeActionButton());

        //click register
        onView(withId(R.id.registerButton1)).perform(click());

        //Confirm the user account was created
        //onView(withId(R.id.)).check(matches(isDisplayed()));



    }

    @After
    public void cleanup(){


    }

}