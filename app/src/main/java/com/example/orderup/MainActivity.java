package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    private String email;
    private String password;

    EditText emailInput;
    EditText passwordInput;

    Button submitButton;

    private String firstName;
    private String lastName;
    private String creditCardNum;
    private int accountBalance;

    //UserData user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                Log.d("this","email " + email + "password " + password);
                openUserAccount();
                //user = new UserAccount(email,password);


                //showToast(email);
                //showToast(password);
            }
        });

    }

    public void openUserAccount() {
        Intent intent = new Intent(this, UserAccount.class);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    private void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}