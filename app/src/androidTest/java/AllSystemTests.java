
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import SystemTests.AddCommentTest;
import SystemTests.AddUserTest;
import SystemTests.LoginTest;


/*
    Purpose: This class runs to test every System Test
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

        LoginTest.class,
        AddCommentTest.class,
        AddUserTest.class,


})
public class AllSystemTests {
}
