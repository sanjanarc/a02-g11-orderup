package com.example.orderup.logic;

public class UserData {


    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String creditcard;
    private String csv;
    private String expiry;
    private String address;

    private float accountBalance;

    public UserData(String email, String password) {

        this.email = email;
        this.password = password;
        accountBalance = 0;
    }

    public void setInfo(String firstname, String lastname, String creditcard,
                        String csv, String expiry, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.creditcard = creditcard;
        this.csv = csv;
        this.expiry = expiry;
        this.address = address;
    }

    public float getBalance() {
        return this.accountBalance;
    }

    public void addBalance(float amount) {
        this.accountBalance += amount;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public String getCsv() {
        return csv;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getAddress() {
        return address;
    }


}
