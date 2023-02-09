package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.persistance.DatabaseHelper;
import com.example.orderup.R;
import com.example.orderup.logic.UserData;

public class UserAccount extends AppCompatActivity {
    DatabaseHelper myDatabase;

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
    Button submitButton3;

    //private float accountBalance;

    UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);

        myDatabase = new DatabaseHelper(this);

        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");
        this.password = intent.getStringExtra("password");
        user = new UserData(this.email,this.password);

        TextView textView = (TextView) findViewById(R.id.accountBalance);
        textView.setText("$" + user.getBalance());

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

                boolean isInserted = myDatabase.insertData(email,password,firstname,lastname,creditcard,csv,expiry,address,user.getBalance());
                if(isInserted) {
                    Log.d("this","USER DATA SUCCESSFULLY ADDED");
                    //user.print();
                } else {
                    Log.d("this","USER DATA FAILED TO BE ADDED");
                }
                //showToast(email);
                //showToast(password);
            }
        });

        submitButton3 = (Button) findViewById(R.id.submitButton3);
        submitButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rand = (int)Math.floor(Math.random() * (5) + 1);
                switch(rand) {
                    case 1: user.addBalance(5.00F);
                        break;
                    case 2: user.addBalance(10.00F);
                        break;
                    case 3: user.addBalance(20.00F);
                        break;
                    case 4: user.addBalance(50.00F);
                        break;
                    case 5: user.addBalance(100.00F);
                        break;
                }
                textView.setText("$" + user.getBalance());
            }
        });


    };


}
