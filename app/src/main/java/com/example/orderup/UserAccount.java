package com.example.orderup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserAccount extends AppCompatActivity {


    private String firstname;
    private String lastname;
    private String creditcard;
    private String csv;
    private String expiry;
    private String address;
    private String email;
    private String password;

    EditText firstNameInput;
    EditText lastNameInput;
    EditText cardNumberInput;
    EditText csvInput;
    EditText expiryInput;
    EditText addressInput;

    Button submitButton2;

    private int accountBalance;

    UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);

        Intent intent = getIntent();

        this.email = intent.getStringExtra("email");
        this.password = intent.getStringExtra("password");

        user = new UserData(this.email,this.password);

        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);
        cardNumberInput = (EditText) findViewById(R.id.cardNumberInput);
        csvInput = (EditText) findViewById(R.id.csvInput);
        expiryInput = (EditText) findViewById(R.id.expiryInput);
        addressInput = (EditText) findViewById(R.id.addressInput);

        submitButton2 = (Button) findViewById(R.id.submitButton2);
        submitButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstname = firstNameInput.getText().toString();
                lastname = lastNameInput.getText().toString();
                creditcard = cardNumberInput.getText().toString();
                csv = csvInput.getText().toString();
                expiry = expiryInput.getText().toString();
                address = addressInput.getText().toString();

                user.setInfo(firstname, lastname, creditcard, csv, expiry, address);
                user.print();
                //showToast(email);
                //showToast(password);
            }
        });

    };


}
