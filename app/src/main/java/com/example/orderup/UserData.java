package com.example.orderup;

import android.util.Log;

public class UserData {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String creditcard;
    private String csv;
    private String expiry;
    private String address;


    UserData(String email, String password) {

        this.email = email;
        this.password = password;

    }

    void setInfo(String firstname, String lastname, String creditcard,
                 String csv, String expiry, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.creditcard = creditcard;
        this.csv = csv;
        this.expiry = expiry;
        this.address = address;
    }

    void print() {
        Log.d("this","email = " + email);
        Log.d("this","password = " + password);
        Log.d("this","firstname = " + firstname);
        Log.d("this","lastname = " + lastname);
        Log.d("this","creditcard = " + creditcard);
        Log.d("this","csv " + csv);
        Log.d("this","expiry " + expiry);
        Log.d("this","address " + address);
    }
}
