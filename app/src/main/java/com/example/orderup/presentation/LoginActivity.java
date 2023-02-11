package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.logic.UserVerification;

public class LoginActivity extends AppCompatActivity
{
    private Button signInButton, registerButton;

    private String email, password;

    private EditText emailInput, passwordInput;

    private UserVerification verify;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verify=new UserVerification();

        //Get email and password.
        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        signInButton= (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();

                if(verify.loginVerification(email, password, LoginActivity.this))
                {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email", email);

                    //Start the main page activity.
                    startActivity(intent);

                    //Remove current activity.
                    finish();
                }
            }
        });

        registerButton= (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Start the register page activity.
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                //Remove current activity.
                finish();
            }
        });
    }
}