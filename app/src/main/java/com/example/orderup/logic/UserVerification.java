package com.example.orderup.logic;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;
import android.app.Activity;
import com.example.orderup.presentation.ErrorPopUp;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.INotificationSideChannel;

public class UserVerification {

    private UserPersistence userPersistence;

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

    public boolean registerAccount(String email, String firstName, String lastName, String password, String rePassword, Context con){
        User tempUser= userPersistence.getUserList().get(email);

        //Email doesn't exist, can create account.
        if(tempUser== null){

            if(password.equals(rePassword)){
                userPersistence.addUser(email, new User(firstName, lastName, email, password));
                return true;
            }else {
                //Password do not match.
                ErrorPopUp er=new ErrorPopUp();
                er.errorMsg(con, "Passwords do not match");
                return false;
            }
        }else {
            //The email is already exist.
            return false;
        }

    }

    public boolean EmailCheck(String email) {
        boolean flag = false;
        int counter = 1;
        char at = '@';
        while(email.length()-1 > counter && !flag)
        {
            if(email.charAt(counter) == at) {
              flag = true;
            }
            counter++;
        }
        return flag;
    }
}
