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
import com.example.orderup.logic.UserVerification;
import com.example.orderup.persistance.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private Button signInButton, registerButton, viewDB;

    private String email, password;

    private EditText emailInput, passwordInput;

    private int userId;
    private UserVerification verify=new UserVerification();

    DatabaseHelper myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDatabase = new DatabaseHelper(this);

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

                if(email.equals("") || password.equals("")){
                    ErrorPopUp er = new ErrorPopUp();
                    er.errorMsg(LoginActivity.this, "Email or Password Is Empty");
                }
                else if (!verify.EmailCheck(email)) {
                    ErrorPopUp er = new ErrorPopUp();
                    Log.d("this", "email:" +email +"    "+password);
                    er.errorMsg(LoginActivity.this, "Incorrect Email Format");
                }
                else if (verify.login(email, password, LoginActivity.this) != null || (null != searchByEmail(email) && checkPassword(email,password))) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    //throw the user id to main_activity to retrieve the user info from database
                    intent.putExtra("email", email);
                    Log.d("this", "go to main");
                    //activate the main class***
                    startActivity(intent);

                    //This is to prevent user back to login page using the back button
                    finish();
                } else {
                    ErrorPopUp er = new ErrorPopUp();
                    er.errorMsg(LoginActivity.this, "Incorrect Email or Password.");
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

        viewDB = (Button) findViewById(R.id.viewDB);
        viewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                printDatabase();
            }
        });
    }
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

    // VV MOVE THESE TWO TO VERIFICATION VV
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

    public boolean checkPassword(String email, String password) {
        //String currId = null;
        boolean match = false;
        boolean found = false;
        Cursor res = myDatabase.getAllData();
        while(res.moveToNext() && found == false) {
            if(email.equals(res.getString(1))) {
                found = true;
                //currId = res.getString(0);
                if(res.getString(2).equals(password)) {
                    match = true;
                }
            }
        }
        return match;
    }

}