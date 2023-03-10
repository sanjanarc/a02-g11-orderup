package com.example.orderup.Objects;

import java.util.Objects;

public class User
{
    private String firstName;
    private String lastName;
    private final String email;
    private String password;
    private String expiry;
    private String address;
    private String creditCard;
    private String cvc;
    private float balance;

    public User(String email, String password, String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCard = "";
        this.cvc = "";
        this.expiry = "";
        this.balance = 0.00F;
    }

    public User(String email, String password, String firstName, String lastName, String creditCard, String cvc, String expiry, String address, String balance)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.cvc = cvc;
        this.expiry = expiry;
        this.address = address;
        if(null == balance) {
            balance = "0.00F";
        }
        this.balance = Float.parseFloat(balance);
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getCreditCard()
    {
        return creditCard;
    }

    public String getCvc()
    {
        return cvc;
    }

    public String getExpiry()
    {
        return expiry;
    }

    public String getAddress()
    {
        return address;
    }

    public void updateFirstName(String newFirstName)
    {
        this.firstName= newFirstName;
    }

    public void updateLastName(String newLastName)
    {
        this.lastName= newLastName;
    }

    public void updatePass(String newPassword)
    {
        this.password= newPassword;
    }

    public void updateAddress(String newAddress)
    {
        this.address= newAddress;
    }

    public void addCreditCard(String newCreditCard, String newCvc, String newExpiry)
    {
        this.creditCard = newCreditCard;
        this.cvc = newCvc;
        this.expiry = newExpiry;
    }

    public void modifyBalance(float balance)
    {
        this.balance += balance;
    }

    public String getBalance()
    {
        return String.valueOf(this.balance);
    }

    public String toString(){
        return String.format("First name: %s\n" +
                "Last name: %s\n" +
                "Email: %s\n" +
                "Password: %s\n" +
                "Address: %s", firstName, lastName, email, password, address);
    }

    public boolean equals(Object other) {
        boolean equals = false;

        if (other instanceof User) {
            final User otherStudent = (User) other;
            equals = Objects.equals(this.email, otherStudent.email);
        }

        return equals;
    }
}
