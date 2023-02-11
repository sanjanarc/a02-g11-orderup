package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.logic.UserVerification;

public class RegisterActivity extends AppCompatActivity {

    private UserVerification verify;

    private String firstName, lastName, email, password, rePassword;

    EditText firstNameInput, lastNameInput, emailInput, passwordInput, rePasswordInput;
    Button registerButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        emailInput= (EditText) findViewById(R.id.emailInput);
        passwordInput= (EditText) findViewById(R.id.passwordInput);
        rePasswordInput= (EditText) findViewById(R.id.rePasswordInput);

        verify= new UserVerification();

        registerButton = (Button) findViewById(R.id.registerButton1);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = firstNameInput.getText().toString();
                lastName = lastNameInput.getText().toString();
                email= emailInput.getText().toString();
                password= passwordInput.getText().toString();
                rePassword= rePasswordInput.getText().toString();

                if(verify.registerAccount(email, firstName, lastName, password, rePassword, RegisterActivity.this)) {
                    //Direct go to main page if register successful.
                    Intent intent= new Intent(RegisterActivity.this, MainActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);

                    //Remove this activity.
                    finish();
                    Log.d("this","USER DATA SUCCESSFULLY ADDED");
                } else {
                    //Get msg from verify for why cannot register.
                    Log.d("this","USER DATA FAILED TO BE ADDED");
                }
            }
        });

        //Back to Login page.
        backButton= (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
