package com.example.orderup.logic;

import static com.example.orderup.logic.Services.getUserPersistence;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

//This class will pass User data from persistence to presentation: User First and Last name, Address,and Wallet Balance
public class UserServices
{

    private static UserPersistence userPersistence = Services.getUserPersistence();

    private static User user = getUserPersistence().getUserTable().get(Services.getCurrentUser());

    //return user object
    public static User getUser()
    {
        return user;
    }

    //Return the first name of the given email.
    public static String getFirstName(String email)
    {
        User user = userPersistence.getUserTable().get(email);

        if(user != null)
        {
            return user.getFirstName();
        }else
        {
            return null;
        }
    }

    //Return the last name of the given email.
    public static String getLastName(String email)
    {
        User user = userPersistence.getUserTable().get(email);

        if(user != null)
        {
            return user.getLastName();
        }else
        {
            return null;
        }
    }

    //Return the Address of the given email.
    public static String getAddress(String email)
    {
        User user = userPersistence.getUserTable().get(email);

        if(user != null)
        {
            return user.getAddress();
        }else
        {
            return null;
        }
    }

    public static String getBalance(String email)
    {
        User user = userPersistence.getUserTable().get(email);

        if(user != null)
        {
            return String.valueOf(user.getBalance());
        }else
        {
            return null;
        }
    }

    public static String getCredit(String email)
    {
        User user = userPersistence.getUserTable().get(email);

        if(user != null)
        {
            return user.getCreditCard();
        }else
        {
            return null;
        }
    }

    public static String getCvc(String email)
    {
        User user = userPersistence.getUserTable().get(email);

        if(user != null)
        {
            return user.getCvc();
        }else
        {
            return null;
        }
    }

    public static String getExpiry(String email)
    {
        User user = userPersistence.getUserTable().get(email);

        if(user != null)
        {
            return user.getExpiry();
        }else
        {
            return null;
        }
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
