package com.example.orderup.logic;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

//This class will pass User data from persistence to presentation: User First and Last name, Address,and Wallet Balance
public class UserServices
{
    private static UserPersistence userPersistence = Services.getUserPersistence();

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
}
