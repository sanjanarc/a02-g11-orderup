package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.logic.UserVerification;

public class RegisterActivity extends AppCompatActivity
{
    private UserVerification verify;

    private String firstName, lastName, email, password, rePassword;

    private EditText firstNameInput, lastNameInput, emailInput, passwordInput, rePasswordInput;

    private Button registerButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        verify= new UserVerification();

        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        emailInput= (EditText) findViewById(R.id.emailInput);
        passwordInput= (EditText) findViewById(R.id.passwordInput);
        rePasswordInput= (EditText) findViewById(R.id.rePasswordInput);

        registerButton = (Button) findViewById(R.id.registerButton1);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                firstName = firstNameInput.getText().toString();
                lastName = lastNameInput.getText().toString();
                email= emailInput.getText().toString();
                password= passwordInput.getText().toString();
                rePassword= rePasswordInput.getText().toString();

                if(verify.registrationVerification(email, firstName, lastName, password, rePassword, RegisterActivity.this))
                {
                    Intent intent= new Intent(RegisterActivity.this, MainActivity.class);
                    intent.putExtra("email", email);

                    //Start the main activity.
                    startActivity(intent);

                    //Remove current activity.
                    finish();
                }
            }
        });

        //Back to Login page.
        backButton= (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                //Remove current activity.
                finish();
            }
        });
    }
}