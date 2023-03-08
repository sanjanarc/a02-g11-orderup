package com.example.orderup.logic;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

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
            User tempUser= userPersistence.getUserTable().get(email);

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
        //First name cannot more than 7 character.
        else if(firstName.length() > 7)
        {
            msg = "Your first name is more than 7 character.";
        }
        //Last name cannot more than 7 character.
        else if(lastName.length() > 7)
        {
            msg = "Your last name is more than 7 character.";
        }
        else
        {
            //Search the input email from database.
            User tempUser= userPersistence.getUserTable().get(email);

            if(tempUser != null) //Registered email is already exist in the database and cannot be used again.
            {
                msg = "Email already in use.";
            }
            else
            {
                //Create a user to stub or database.
                userPersistence.addUser(email, firstName, lastName, password);
                msg = null;
            }
        }
        return msg;
    }

    //Verify the input credit card format.
    public static String creditCardVerification(String email, String cardNum, String cardCvc, String cardExpiry)
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
                //Add the credit card to the database.
                userPersistence.addCreditCard(email, cardNum, cardCvc, cardExpiry);
                msg = "Credit Card added.";
            }
        }
        else
        {
            msg = "Missing Field: Please check you have entered all fields.";
        }
        return msg;
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
    //Verity the input address format and return the message to user.
    public static String addressVerification(String street, String city, String province, String postal, String email, String address)
    {
        userPersistence = Services.getUserPersistence();

        String result = streetVerification(street) + cityVerification(city) + provinceVerification(province) + postalVerification(postal);
        if(result == "")
        {
            //No error occur than add the address to database.
            userPersistence.updateAddress(email, address);
            return "Address added.";
        }
        else
        {
            return result;
        }
    }

    //Check the street section of the address.
    private static String streetVerification(String street) {

        if(street == "" || street == null)
        {
            return  "Error: Address format incorrect.\n";
        }
        else
        {
            return "";
        }
    }

    //Check the city is Winnipeg or not.
    private static String cityVerification(String city) {

        if(!city.equalsIgnoreCase("Winnipeg"))
        {
            return "Error: The city you entered must be located within Manitoba.\n";
        }
        else
        {
            return "";
        }
    }

    //Check the province is Manitoba or other province.
    private static String provinceVerification(String province) {

        if(!province.equalsIgnoreCase("Manitoba"))
        {
            return  "Error: Currently does not support other province other than Manitoba.\n";
        }
        else
        {
            return "";
        }
    }

    //Check the format of the postal code.
    private static String postalVerification(String postal) {
    // change later
        return "";
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
