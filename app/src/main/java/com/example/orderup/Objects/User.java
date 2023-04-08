package com.example.orderup.Objects;

import java.util.ArrayList;
import java.util.Objects;
/**
 * This class holds a single User object.
 */
public class User extends FoodItem
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

    private ArrayList<FoodItem> cart = new ArrayList<>();
    private ArrayList<ArrayList<FoodItem>> OrderHistory = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param firstName the new user's first name.
     * @param lastName the new user's last name.
     * @param email the new user's email.
     * @param password the new user's password.
     */
    public User(String firstName, String lastName, String email, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCard = "";
        this.cvc = "";
        this.expiry = "";
        this.balance = 0.00F;
        cart = new ArrayList<>();
    }

    /**
     * Constructor.
     *
     * @param email the new user's email.
     * @param password the new user's password.
     * @param firstName the new user's first name.
     * @param lastName the new user's last name.
     * @param creditCard the new user's creditcard number.
     * @param cvc the new user's cvc number.
     * @param expiry the new user's creditcard expiry date.
     * @param address the new user's address.
     * @param balance the new user's balance.
     */
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

        this.cart = new ArrayList<>();
        OrderHistory = new ArrayList<ArrayList<FoodItem>>();
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

    public float getBalance()
    {
        return this.balance;
    }

    public String toString(){
        return String.format("First name: %s\n" +
                "Last name: %s\n" +
                "Email: %s\n" +
                "Password: %s\n" +
                "Address: %s", firstName, lastName, email, password, address);
    }

    /**
     * Checks two emails to see if they are the samel.
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
     * Adds a food item to the user's cart.
     *
     * @param foodItem the foodItem to add.
     * @param number the amount of that item to add.
     */
    public void addToFoodCart(FoodItem foodItem, int number)
    {
        cart.add(foodItem);
        foodItem.setNumItems(number);
    }

    public ArrayList<FoodItem> getFoodCart()
    {
        return cart;
    }

    public void clearFoodCart()
    {
        cart.clear();
    }

    public ArrayList<ArrayList<FoodItem>> getOrderHistory()
    {
        return OrderHistory;
    }

    public void removeFoodFromCart(FoodItem foodItem, int num) {

    }
}