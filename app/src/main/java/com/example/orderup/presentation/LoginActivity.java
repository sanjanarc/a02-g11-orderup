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

public class LoginActivity extends AppCompatActivity {

    private Button signInButton, registerButton;

    private String email, password;

    private EditText emailInput, passwordInput;

    private int userId;
    private UserVerification verify=new UserVerification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get email and password.
        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        //Response of Sign in button.
        signInButton= (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();

                if(verify.login(email, password) != null){
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);

                    //throw the user id to main_activity to retrieve the user info from database
                    intent.putExtra("email", email);
                    Log.d("this", "go to main");
                    //activate the main class***
                    startActivity(intent);

                    //This is to prevent user back to login page using the back button
                    finish();
                }else {
                    //Get some error msg from verify class and show to user.
                }
            }
        });

        //Response of Register button
        registerButton= (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to Register page.
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }
}