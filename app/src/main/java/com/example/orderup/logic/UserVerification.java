package com.example.orderup.logic;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

public class UserVerification
{

    private static UserPersistence userPersistence;

    public static String loginVerification(String email, String password)
    {
        //Store the message going to return to presentation layer.
        String msg;

        //Get stub database.
        userPersistence = Services.getUserPersistence();

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
            User tempUser= userPersistence.getUserList().get(email);

            //Only true if the email exist in the database.
            if(tempUser != null)
            {
                if(tempUser.getPassword().equals(password))
                {
                    msg =  null;
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

    public static String registrationVerification(String email, String firstName, String lastName, String password, String rePassword)
    {
        //Store the message going to return to presentation layer.
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
            User tempUser= userPersistence.getUserList().get(email);

            if(tempUser != null)
            {
                msg = "Email already in use.";
            }
            else
            {
                userPersistence.addUser(email, new User(firstName, lastName, email, password));
                msg = null;
            }
        }
        return msg;
    }

    public static String creditCardVerification(String email, String cardNum, String cardCvc, String cardExpiry)
    {
        //Store the message going to return to presentation layer.
        String msg;

        //Inputs cannot be empty.
        if(!(cardNum.equals("") || cardCvc.equals("") || cardExpiry.equals("")))
        {
            if(cardNum.length() != 16)
            {
                msg = "Error: Incorrect Card Number Format.";
            }
            else if(cardNum.charAt(0) != '2' && cardNum.charAt(0) != '3'
                    && cardNum.charAt(0) != '4' && cardNum.charAt(0) != '5')
            {
                msg = "Error: Card is not Visa, American Express or Mastercard.";
            }
            else if(cardCvc.length() != 3 && cardCvc.length() != 4)
            {
                msg = "Error: Incorrect CVC length.";
            }
            else if(cardExpiry.length() != 5)
            {
                msg = "Error: Incorrect Expiry date length.";
            }
            else if(cardExpiry.charAt(2) != '/' || (cardExpiry.charAt(0) != '0' && cardExpiry.charAt(0) != '1')
                    || (cardExpiry.charAt(0) == '1' && Character.getNumericValue(cardExpiry.charAt(1)) >= 3))
            {
                msg = "Error: Incorrect Expiry date.";
            }
            else
            {
                userPersistence.addCreditCard(email, cardNum, cardCvc, cardExpiry);
                msg = null; //Card added successful.
            }
        }
        else
        {
            msg = "Missing Field: Please check you have entered all fields.";
        }
        return msg;
    }

    public static String addressVerification(String email, String address)
    {
        //Store the message going to return to presentation layer.
        String msg;

        if(!address.equals(""))
        {
            String[] tempAddress= address.split(", ");

            if(tempAddress.length != 4)
            {
                msg = "Error: Address format incorrect.";
            }
            else if(!tempAddress[1].equalsIgnoreCase("Winnipeg"))
            {
                msg = "Error: City must within Manitoba.";
            }
            else if(!tempAddress[2].equalsIgnoreCase("Manitoba"))
            {
                msg = "Error: Currently does not support other province other than Manitoba.";
            }
            else if(false)
            {
                //Add postal code error.
            }
            else
            {
                userPersistence.updateAddress(email, address);
                msg = null; //Address added successful.
            }
        }
        else
        {
            msg = "Missing Field: Please fill in the blank.";
        }

        return msg;
    }

    //Make sure the email input contain character "@".
    public static boolean emailCheck(String email)
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
