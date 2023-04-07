package com.example.orderup.logic;

import static com.example.orderup.logic.Services.getUserPersistence;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

/**
 * This class provided method/services that related to user.
 */
public class UserServices {

    public UserPersistence userPersistence;
    public User user;

    /**
     * Constructor.
     *
     * @param userPersistence the user database.
     * @param email the user email.
     */
    public UserServices(UserPersistence userPersistence, String email) {

        //Dependency injection.
        this.userPersistence = userPersistence;
        user = this.userPersistence.getUser(email);
    }

    /**
     * Return user object.
     *
     * @return current user.
     */
    public User getUser() {

        if (user != null){
            return user;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Return the first name of current user.
     *
     * @return current user first name.
     */
    public String getFirstName() {
        return user.getFirstName();
    }

    /**
     * Return the last name of the current user.
     *
     * @return current user last name.
     */
    public String getLastName() {
        return user.getLastName();
    }

    /**
     * Return the address of current user.
     *
     * @return current user address.
     */
    public String getAddress() {
        return user.getAddress();
    }

    /**
     * Return the balance of current user.
     *
     * @return current user balance.
     */
    public String getBalance() {
        return String.valueOf(user.getBalance());
    }

    /**
     * Return credit card number of current user.
     *
     * @return credit card number.
     */
    public String getCredit() {
        return user.getCreditCard();
    }

    /**
     * Return credit card cvc.
     *
     * @return cvc of the credit card.
     */
    public String getCvc() {
        return user.getCvc();
    }

    /**
     * Return expiry date of the credit card.
     *
     * @return expiry date.
     */
    public String getExpiry() {
        return user.getExpiry();
    }

    public static boolean FoodItemExists(FoodItem foodItem) {
        for(int i = 0; i< getUser().getFoodCart().size(); i++)
        {
            if(getUser().getFoodCart().get(i).equals(foodItem))
                return true;
        }
        return false;
    }
}
