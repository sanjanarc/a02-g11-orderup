package com.example.orderup.presentation;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.R;
import com.example.orderup.logic.UserData;
import com.example.orderup.persistance.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper myDatabase;

    //private VerifyService vr;

    private String firstName, lastName, email, password, rePassword;

    private String creditcard;
    private String csv;
    private String expiry;
    private String address;

    EditText firstNameInput, lastNameInput, emailInput, passwordInput, rePasswordInput;
    Button registerButton, backButton, viewButton;
    EditText cardNumberInput;
    EditText csvInput;
    EditText expiryInput;
    EditText addressInput;

    //private float accountBalance;

    UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDatabase = new DatabaseHelper(this);

        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");
        this.password = intent.getStringExtra("password");
        user = new UserData(this.email,this.password);


        firstNameInput = (EditText) findViewById(R.id.firstNameInput);
        lastNameInput = (EditText) findViewById(R.id.lastNameInput);



        //Do we really need credit card when registering.
        //cardNumberInput = (EditText) findViewById(R.id.cardNumberInput);
        //csvInput = (EditText) findViewById(R.id.csvInput);
        //expiryInput = (EditText) findViewById(R.id.expiryInput);
        //addressInput = (EditText) findViewById(R.id.addressInput);

        registerButton = (Button) findViewById(R.id.registerButton1);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //firstName = firstNameInput.getText().toString();
                //lastName = lastNameInput.getText().toString();
                //email= emailInput.getText().toString();
                //password= passwordInput.getText().toString();
                //rePassword= rePasswordInput.getText().toString();


                //creditcard = cardNumberInput.getText().toString();
                //csv = csvInput.getText().toString();
                //expiry = expiryInput.getText().toString();
                //address = addressInput.getText().toString();
                //user.setInfo(firstName, lastName, creditcard, csv, expiry, address);

                //boolean isInserted = myDatabase.insertData(email,password,firstname,lastname,creditcard,csv,expiry,address,user.getBalance());
                if(true) {
                    //Direct go to main page if register successful.
                    Intent intent= new Intent(RegisterActivity.this, MainActivity.class);
                    //intent.putExtra("userID", userID);
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

        //Get a view of Database
        viewButton = (Button) findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printDatabase();
            }
        });
    };

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void printDatabase() {
        // Print database contents
        Cursor res = myDatabase.getAllData();
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("Id :" + res.getString(0) + "\n");
            buffer.append("Email :" + res.getString(1) + "\n");
            buffer.append("Password :" + res.getString(2) + "\n");
            buffer.append("First Name :" + res.getString(3) + "\n");
            buffer.append("Last Name :" + res.getString(4) + "\n");
            buffer.append("Credit Card :" + res.getString(5) + "\n");
            buffer.append("CSV :" + res.getString(6) + "\n");
            buffer.append("Expiry :" + res.getString(7) + "\n");
            buffer.append("Address :" + res.getString(8) + "\n");
            buffer.append("Account Balance :$" + res.getString(9) + "\n\n");
        }

        //Show all data
        showMessage("Data",buffer.toString());

    }

}
