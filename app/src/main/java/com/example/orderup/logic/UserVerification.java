package com.example.orderup.logic;

import android.content.Context;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.presentation.ErrorPopUp;

public class UserVerification
{

    private UserPersistence userPersistence;

    //Constructor.
    public UserVerification()
    {
        userPersistence= Services.getUserPersistence();
    }

    public boolean loginVerification(String email, String password, Context context)
    {
        //Input cannot be empty.
        if(email.equals("") || password.equals(""))
        {
            ErrorPopUp.errorMsg(context, "Email or Password is Empty.");
        }
        //Email format must meet standard format.
        else if(!emailCheck(email))
        {
            ErrorPopUp.errorMsg(context, "Incorrect Email Format.");
        }
        //Compare the entered password with the account password.
        else
        {
            User tempUser= userPersistence.getUserList().get(email);

            //Only true if the email exist in the database.
            if(tempUser != null)
            {
                if(tempUser.getPassword().equals(password))
                {
                    return true;
                }
                else
                {
                    ErrorPopUp.errorMsg(context, "Incorrect Password.");
                }
            }
            else
            {
                ErrorPopUp.errorMsg(context, "Email does not exist.");
            }
        }
        return false;
    }

    public boolean registrationVerification(String email, String firstName, String lastName, String password, String rePassword, Context context)
    {
        //Inputs cannot be empty.
        if(firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("") || rePassword.equals(""))
        {
            ErrorPopUp.errorMsg(context, "Missing Field: Please check you have entered all fields.");
        }
        //Email format must meet standard format.
        else if(!emailCheck(email))
        {
            ErrorPopUp.errorMsg(context, "Incorrect Email Format.");
        }
        //Password must be the same.
        else if(!password.equals(rePassword))
        {
            ErrorPopUp.errorMsg(context, "Password and Re-password do not match.");
        }
        //Password must more than 6 character.
        else if(password.length() < 6)
        {
            ErrorPopUp.errorMsg(context, "Password needs to be at least 6 characters long.");
        }
        else
        {
            User tempUser= userPersistence.getUserList().get(email);

            if(tempUser != null)
            {
                ErrorPopUp.errorMsg(context, "Email already in use.");
            }
            else
            {
                userPersistence.addUser(email, new User(firstName, lastName, email, password));
                return true;
            }
        }
        return false;
    }

    public boolean creditCardVerification(String cardNum, String cardCvc, String cardExpiry, Context context)
    {
        //Inputs cannot be empty.
        if(!(cardNum.equals("") || cardCvc.equals("") || cardExpiry.equals("")))
        {
            if(cardNum.length() != 16)
            {
                ErrorPopUp.errorMsg(context, "Error: Incorrect Card Number Format.");
            }
            else if(cardNum.charAt(0) != '2' && cardNum.charAt(0) != '3'
                    && cardNum.charAt(0) != '4' && cardNum.charAt(0) != '5')
            {
                ErrorPopUp.errorMsg(context, "Error: Card is not Visa, American Express or Mastercard.");
            }
            else if(cardCvc.length() != 3 && cardCvc.length() != 4)
            {
                ErrorPopUp.errorMsg(context, "Error: Incorrect CVC length.");
            }
            else if(cardExpiry.length() != 5)
            {
                ErrorPopUp.errorMsg(context, "Error: Incorrect Expiry date length.");
            }
            else if(cardExpiry.charAt(2) != '/' || (cardExpiry.charAt(0) != '0' && cardExpiry.charAt(0) != '1')
                    || (cardExpiry.charAt(0) == '1' && Character.getNumericValue(cardExpiry.charAt(1)) >= 3))
            {
                ErrorPopUp.errorMsg(context, "Error: Incorrect Expiry date.");
            }
            else
            {
                ErrorPopUp.errorMsg(context, "Credit Card added.");
                return true;
            }
        }
        else
        {
            ErrorPopUp.errorMsg(context, "Missing Field: Please check you have entered all fields.");
        }
        return false;
    }

    public boolean addressVerification(String address, Context context)
    {
        if(!address.equals(""))
        {
            String[] tempAddress= address.split(", ");

            if(tempAddress.length != 4)
            {
                ErrorPopUp.errorMsg(context, "Error: Address format incorrect.");
            }
            else if(!tempAddress[1].equalsIgnoreCase("Winnipeg"))
            {
                ErrorPopUp.errorMsg(context, "Error: The city you entered must be located within Manitoba.");
            }
            else if(!tempAddress[2].equalsIgnoreCase("Manitoba"))
            {
                ErrorPopUp.errorMsg(context, "Error: Currently does not support other province other than Manitoba.");
            }
            else if(false)
            {
                //Add postal code error.
            }
            else
            {
                ErrorPopUp.errorMsg(context, "Address been added.");
            }
        }
        else
        {
            ErrorPopUp.errorMsg(context, "Missing Field: Please fill in the blank.");
            return true;
        }

        return false;
    }

    //Make sure the email input contain character "@".
    public boolean emailCheck(String email)
    {
        boolean flag = false;
        int counter = 1;
        char at = '@';

        while(email.length()-1 > counter && !flag)
        {
            if(email.charAt(counter) == at)
            {
                flag = true;
            }
            counter++;
        }
        return flag;
    }
}
