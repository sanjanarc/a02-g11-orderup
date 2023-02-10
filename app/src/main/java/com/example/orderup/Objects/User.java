package com.example.orderup.Objects;

import java.util.Objects;

public class User {

    private String firstName, lastName, email, password, expiry, address;
    private int creditCard, cvc;

    public User(String firstName, String lastName, String email, String password) {

        this.firstName= firstName;
        this.lastName= lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getAddress(){
        return address;
    }

    public void updateFirstName(String newFirstName){
        this.firstName= newFirstName;
    }

    public void updateLastName(String newLastName){
        this.lastName= newLastName;
    }

    public void updatePass(String newPassword){
        this.password= newPassword;
    }

    public void updateAddress(String newAddress){
        this.address= newAddress;
    }

    public void addCreditCard(int newCreditCard, int newCvc, String newExpiry){
        this.creditCard= newCreditCard;
        this.cvc= newCvc;
        this.expiry= newExpiry;
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
