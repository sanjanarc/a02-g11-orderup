package SystemTests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;

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

/*
    Purpose: Test adding a comment in a restaurant's comment section
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddCommentTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> loginActivity = new ActivityScenarioRule<LoginActivity>(LoginActivity.class);
    @Test
    public void testComment(){

        //login
        onView(withId(R.id.emailInput)).perform(typeText("admin2@email.com"));
        onView(withId(R.id.passwordInput)).perform(typeText("admin123"));
        onView(withId(R.id.signInButton)).perform(click());

        //click on the first restaurant in the list on the home page
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //leave a comment
        onView(withId(R.id.comment_Button)).perform(click());
        onView(withId(R.id.inputComment)).perform(typeText("10/10"));
        onView(withId(R.id.sendComment)).perform(click());
        //check the comment was left in the comment section
        pressBack();
        pressBack();
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.comment_Button)).perform(click());
        onView(withId(R.id.commentSection)).check(matches(withText(containsString("10/10"))));
    }

}


