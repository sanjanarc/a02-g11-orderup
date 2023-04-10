package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.logic.MyException;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserVerification;

/**
 * Register Interface.
 */
public class RegisterActivity extends AppCompatActivity {

    private String firstName, lastName, email, password, rePassword;
    private Button registerButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Event listener of the register button.
        registerButton = (Button) findViewById(R.id.registerButton1);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create the user verification object and passing the database.
                UserVerification userVerification = new UserVerification(Services.getUserPersistence());

                //Get input data from xml file.
                firstName = ((EditText) findViewById(R.id.firstNameInput)).getText().toString();
                lastName = ((EditText) findViewById(R.id.lastNameInput)).getText().toString();
                email = ((EditText) findViewById(R.id.emailInput)).getText().toString();
                password = ((EditText) findViewById(R.id.passwordInput)).getText().toString();
                rePassword = ((EditText) findViewById(R.id.rePasswordInput)).getText().toString();

                try {

                    // Verify the input data with databases. Will add the account to databases and login the application if input data are correct.
                    userVerification.registrationVerification(email, firstName, lastName, password, rePassword);

                    // Tell the system who is the current user.
                    Services.setCurrentUser(email);

                    // Move to the home page.
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                    // Remove current activity.
                    finish();

                } catch (
                        Exception e) { // Catch the specific error from verification and display to user.

                    String msg;

                    if (e instanceof MyException.EXCEPTION_EMPTY_INPUT) {

                        msg = "Missing Field: Please check you have entered all fields.";

                    } else if (e instanceof MyException.EXCEPTION_ILLEGAL_FORMAT) {

                        msg = "Incorrect Email Format.";

                    } else if (e instanceof MyException.EXCEPTION_INVALID_PASSWORD) {

                        msg = "Password and Re-password do not match.";

                    } else if (e instanceof MyException.EXCEPTION_PASSWORD_NOT_ENOUGH_LENGTH) {

                        msg = "Password needs to be at least 6 characters long.";

                    } else if (e instanceof MyException.EXCEPTION_INPUT_OVER_FLOW) {

                        msg = "Your first name is more than 7 character.";

                    } else if (e instanceof MyException.EXCEPTION_INPUT_OVER_FLOW2) {

                        msg = "Your last name is more than 7 character.";

                    } else if (e instanceof MyException.EXCEPTION_ITEM_ALREADY_EXIST) {

                        msg = "Email already in use.";

                    } else {

                        msg = e.getMessage();

                    }

                    // Display the error message.
                    ErrorPopUp.errorMsg(RegisterActivity.this, msg);
                }
            }
        });

        // Event listener of the back button.
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Close this activity and back to login activity.
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                // Remove current activity.
                finish();

            }
        });
    }
}