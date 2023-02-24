package com.example.orderup.logic;

import android.content.Context;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.DatabaseHelper;
import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.presentation.ErrorPopUp;

public class UserVerification {

    private UserPersistence userPersistence;

    DatabaseHelper myDatabase;

    public UserVerification(){
        userPersistence= Services.getUserPersistence();
    }

    public String login(String email, String password, Context con1){
        User tempUser= userPersistence.getUserList().get(email);

        if(tempUser != null){
            if(tempUser.getPassword().equals(password)){

                return email;
            }
            else {
                ErrorPopUp er=new ErrorPopUp();
                er.errorMsg(con1, "Email is empty");
            }
        }
        return null;
    }

    public boolean RegistrationVerification(String email, String firstName, String lastName, String password, String rePassword, Context con){
        User tempUser= userPersistence.getUserList().get(email);
        //Email doesn't exist, can create account.
        if(firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("") || rePassword.equals(""))
        {
            ErrorPopUp er = new ErrorPopUp();
            er.errorMsg(con, "Missing Field: Please check you have entered all fields.");
        } else if(!EmailCheck(email))
        {
            ErrorPopUp er = new ErrorPopUp();
            er.errorMsg(con, "Incorrect Email Format");
        } else if(tempUser != null)
        {
            if (email.equals(tempUser.getEmail()))
            {
                ErrorPopUp er = new ErrorPopUp();
                er.errorMsg(con, "Email already in use.");
            }
        } else if(password.equals(rePassword))
        {
            if (password.length() < 6)
            {
                ErrorPopUp er = new ErrorPopUp();
                er.errorMsg(con, "Password needs to be at least 6 characters long.");
            } else {
                userPersistence.addUser(email, new User(firstName, lastName, email, password));
                return true;
            }
        } else
        {
                //Password do not match.
                ErrorPopUp er=new ErrorPopUp();
                er.errorMsg(con, "Passwords do not match");
                return false;
        }
            //The email is already exist.
            return false;
    }



    public boolean EmailCheck(String email)
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
