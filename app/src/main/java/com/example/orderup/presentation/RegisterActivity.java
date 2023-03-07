package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserVerification;

//Register UI class.
public class RegisterActivity extends AppCompatActivity
{
    private String firstName, lastName, email, password, rePassword;

    private EditText firstNameInput, lastNameInput, emailInput, passwordInput, rePasswordInput;

    private Button registerButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Connect to the xml file.
        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        emailInput= (EditText) findViewById(R.id.emailInput);
        passwordInput= (EditText) findViewById(R.id.passwordInput);
        rePasswordInput= (EditText) findViewById(R.id.rePasswordInput);

        //Event listener of the register button.
        registerButton = (Button) findViewById(R.id.registerButton1);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Get input data from xml file.
                firstName = firstNameInput.getText().toString();
                lastName = lastNameInput.getText().toString();
                email= emailInput.getText().toString();
                password= passwordInput.getText().toString();
                rePassword= rePasswordInput.getText().toString();

                //Verify the input data with databases. Will add the account to databases and login the application if input data are correct.
                String result = UserVerification.registrationVerification(email, firstName, lastName, password, rePassword);
                if(result == null) //Account created successful.
                {
                    Intent intent= new Intent(RegisterActivity.this, MainActivity.class);

                    //Tell the system who is the current user.
                    Services.setCurrentUser(email);

                    //Start the main activity.
                    startActivity(intent);

                    //Remove current activity.
                    finish();
                }
                else
                {
                    //Warning user something went wrong.
                    ErrorPopUp.errorMsg(RegisterActivity.this, result);
                }
            }
        });

        //Event listener of the back button.
        backButton= (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Close this activity and back to login activity.
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                //Remove current activity.
                finish();
            }
        });
    }
}