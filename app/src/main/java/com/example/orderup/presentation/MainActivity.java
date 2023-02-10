package com.example.orderup.presentation;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.persistance.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private String email;
    private String password;

    EditText emailInput;
    EditText passwordInput;

    Button submitButton;
    Button registerButton;

    private String firstName;
    private String lastName;
    private String creditCardNum;
    private int accountBalance;

    //TESTTESTESTSET
    DatabaseHelper myDatabase;

    //UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TESTTESTESTSET
        myDatabase = new DatabaseHelper(this);

        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                Log.d("this","email " + email + "password " + password);
                if(null != searchByEmail(email)) {
                    openUserAccount();
                } else {
                    Log.d("this","Error - Account not Found!");
                }
                //user = new UserAccount(email,password);


                //showToast(email);
                //showToast(password);
            }
        });

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                Log.d("this","email " + email + "password " + password);
                openRegister();
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

    public void openRegister() {
        Intent intent2 = new Intent(this, UserRegister.class);
        intent2.putExtra("email", email);
        intent2.putExtra("password", password);
        startActivity(intent2);
    }

    public String searchByEmail(String email) {
        String currId = null;
        boolean found = false;
        Cursor res = myDatabase.getAllData();
        while(res.moveToNext() && found == false) {
            if(email.equals(res.getString(1))) {
                found = true;
                currId = res.getString(0);
            }
        }
        return currId;
    }


    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}