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
import com.example.orderup.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddGiftCard {
    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivity = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);

    @Test
    public void addGiftC() {
        //enter user's email and passwordad
        onView(withId(R.id.emailInput)).perform(typeText("admin2@email.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("admin123"));
        //click on sign in
        onView(withId(R.id.signInButton)).perform(click());
        closeSoftKeyboard();
        //go to User account
        onView(withId(R.id.user_account)).perform(click()); //click the user account fragment

        onView(withId(R.id.redeemCardButton)).perform(click());
        onView(withId(R.id.redeemInput)).perform(typeText("6748369873539860"));//enter gift card
        onView(withText("Done")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click()); // Perform the click action on "Done"

        //Confirm: This gift card contains $5, confirm the change in Account Balance
        onView(withId(R.id.infoContainer)).check(matches(withText((containsString("$ 5.0")))));


    }
}
