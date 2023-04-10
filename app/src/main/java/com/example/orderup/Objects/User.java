package com.example.orderup.Objects;

import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class holds a single User object.
 */
public class User extends FoodItem {

    // User attributes.
    private String firstName;
    private String lastName;
    private final String email;
    private String password;
    private String expiry;
    private String address;
    private String creditCard;
    private String cvc;
    private float balance;
    private Boolean member;

    private List<FoodItem> cart;

    /**
     * Constructor.
     *
     * @param firstName the new user's first name.
     * @param lastName  the new user's last name.
     * @param email     the new user's email.
     * @param password  the new user's password.
     */
    public User(String firstName, String lastName, String email, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCard = "";
        this.cvc = "";
        this.expiry = "";
        this.balance = 0.00F;
        this.member = false;
        cart = new ArrayList<>();

    }

    /**
     * Constructor.
     *
     * @param email      the new user's email.
     * @param password   the new user's password.
     * @param firstName  the new user's first name.
     * @param lastName   the new user's last name.
     * @param creditCard the new user's creditcard number.
     * @param cvc        the new user's cvc number.
     * @param expiry     the new user's creditcard expiry date.
     * @param address    the new user's address.
     * @param balance    the new user's balance.
     * @param member     the status of membership.
     */
    public User(String email, String password, String firstName, String lastName, String creditCard, String cvc, String expiry, String address, String balance, Boolean member) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
        this.cvc = cvc;
        this.expiry = expiry;
        this.address = address;
        this.member = member;
        if (null == balance) {
            balance = "0.00F";
        }
        this.balance = Float.parseFloat(balance);

        this.cart = new ArrayList<>();

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public String getCvc() {
        return cvc;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getMembership() {
        return member;
    }

    public void updateFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public void updateLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public void updatePass(String newPassword) {
        this.password = newPassword;
    }

    public void updateAddress(String newAddress) {
        this.address = newAddress;
    }

    public void addCreditCard(String newCreditCard, String newCvc, String newExpiry) {
        this.creditCard = newCreditCard;
        this.cvc = newCvc;
        this.expiry = newExpiry;
    }

    public float getBalance() {
        return this.balance;
    }

    public String toString() {
        return String.format("First name: %s\n" +
                "Last name: %s\n" +
                "Email: %s\n" +
                "Password: %s\n" +
                "Address: %s", firstName, lastName, email, password, address);
    }

    /**
     * Checks two emails to see if they are the same.
     *
     * @param other the user to compare to this user.
     * @return true if they are the same, false if not.
     */
    public boolean equals(Object other) {

        boolean equals = false;

        if (other instanceof User) {

            final User otherStudent = (User) other;
            equals = Objects.equals(this.email, otherStudent.email);

        }

        return equals;
    }

    /**
     * Get the food list from database.
     *
     * @return a list of food order.
     */
    public List<FoodItem> getFoodCart() {

        cart = new UserServices(Services.getUserPersistence()).getFoodCart(this.email);
        return cart;

    }

    /**
     * Clears Food Cart.
     */
    public void clearFoodCart() {

        cart.clear();
        new UserServices(Services.getUserPersistence()).clearCart(this.email);

    }

    /**
     * Adds a food item to the user's cart.
     *
     * @param foodItem the foodItem to add.
     * @param number   the amount of that item to add.
     */
    public void addToFoodCart(FoodItem foodItem, int number) {

        int rest_id = foodItem.getRestaurant_id();
        int food_id = foodItem.getItem_id();

        new UserServices(Services.getUserPersistence()).updateCart(this.email, rest_id, food_id, number);

    }

    /**
     * Remove the food from cart.
     *
     * @param foodItem the target item.
     * @param num      number of food to remove.
     */
    public void removeFoodFromCart(FoodItem foodItem, int num) {
        int rest_id = foodItem.getRestaurant_id();
        int food_id = foodItem.getItem_id();

        new UserServices(Services.getUserPersistence()).removeFromCart(this.email, rest_id, food_id, num);
    }
}