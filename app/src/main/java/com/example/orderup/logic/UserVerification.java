package com.example.orderup.logic;

import android.content.Context;
import android.util.Log;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.presentation.ErrorPopUp;

public class UserVerification
{
    private static UserPersistence userPersistence;

    //Verify the input email and password from databases. Return null if input data are correct, return error message, otherwise.
    public static String loginVerification(String email, String password)
    {
        //Get the database.
        userPersistence = Services.getUserPersistence();

        //Store message that going to return to presentation layer.
        String msg;

        //Input cannot be empty.
        if(email.equals("") || password.equals(""))
        {
            msg = "Email or Password is Empty.";
        }
        //Email format must meet standard format.
        else if(!emailCheck(email))
        {
            msg = "Incorrect Email Format.";
        }
        //Compare the entered password with the account password.
        else
        {
            //Search the input email from database.
            User tempUser= userPersistence.getUserList().get(email);

            //Only true if the email exist in the database.
            if(tempUser != null)
            {
                if(tempUser.getPassword().equals(password))
                {
                    //Return null mean user exists and the password correct.
                    msg = null;
                }
                else
                {
                    msg = "Incorrect Password.";
                }
            }
            else
            {
                msg = "Email does not exist.";
            }
        }
        return msg;
    }

    //Verify the input data's format and create an account if the data is correct. Return error message if data format is incorrect.
    public static String registrationVerification(String email, String firstName, String lastName, String password, String rePassword)
    {
        //Get the database.
        userPersistence = Services.getUserPersistence();

        //Store message that going to return to presentation layer.
        String msg;

        //Inputs cannot be empty.
        if(firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("") || rePassword.equals(""))
        {
            msg = "Missing Field: Please check you have entered all fields.";
        }
        //Email format must meet standard format.
        else if(!emailCheck(email))
        {
            msg = "Incorrect Email Format.";
        }
        //Password must be the same.
        else if(!password.equals(rePassword))
        {
            msg = "Password and Re-password do not match.";
        }
        //Password must more than 6 character.
        else if(password.length() < 6)
        {
            msg = "Password needs to be at least 6 characters long.";
        }
        else
        {
            //Search the input email from database.
            User tempUser= userPersistence.getUserList().get(email);

            if(tempUser != null) //Registered email is already exist in the database and cannot be used again.
            {
                msg = "Email already in use.";
            }
            else
            {
                //Create a user to stub or database.
                userPersistence.addUser(email, new User(firstName, lastName, email, password));

                msg = null;
            }
        }
        return msg;
    }

    //Verify the input credit card format.
    public static String creditCardVerification(String cardNum, String cardCvc, String cardExpiry)
    {
        //Get the database.
        userPersistence = Services.getUserPersistence();

        //Store message that going to return to presentation layer.
        String msg;

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

    /* old address verification
    public boolean addressVerification(String address, Context context)
    {
        if(!address.equals(""))
        {
            String[] tempAddress= address.split(",| ");

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
                //Add postal code error .
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
*/
    public boolean addressVerification(String address, String city, String province, String postal, Context context)
    {
        return (streetVerification(address, context) && cityVerification(city, context) && provinceVerification(province, context) && postalVerification(postal, context));
    }

    public boolean streetVerification(String address, Context context) {

        if(address == "" || address == null) {
            ErrorPopUp.errorMsg(context, "Error: Address format incorrect.");
            return false;
        }

        return true;
    }

    public boolean cityVerification(String city, Context context) {
        Log.d("this","city = " + city);
        if(!city.equalsIgnoreCase("Winnipeg")) {
            ErrorPopUp.errorMsg(context, "Error: The city you entered must be located within Manitoba.");
            return false;
        }

        return true;
    }

    public boolean provinceVerification(String province, Context context) {
        if(!province.equalsIgnoreCase("Manitoba")) {
            ErrorPopUp.errorMsg(context, "Error: Currently does not support other province other than Manitoba.");
            return false;
        }

        return true;
    }

    public boolean postalVerification(String province, Context context) {
    // change later
    return true;
    }


    //Make sure the email input contain character "@".
    public static boolean emailCheck(String email)
    {
        boolean flag = false;
        boolean checkPeriod = false;
        boolean multiplesAts = false;
        int counter = 1;
        char at = '@';

        while(email.length()-1 > counter)
        {
            if(email.charAt(counter) == at && !flag)
            {
                flag = true;
            } else if(email.charAt(counter) == at && flag) {
                multiplesAts = true;
            }

            if(flag && email.charAt(counter) == '.' && counter < email.length()-1) {
                checkPeriod = true;
            }

            counter++;
        }
        return flag && checkPeriod && !multiplesAts;
    }
}
