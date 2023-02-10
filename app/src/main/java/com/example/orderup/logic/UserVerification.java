package com.example.orderup.logic;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;


public class UserVerification {

    private UserPersistence userPersistence;

    public UserVerification(){
        userPersistence= Services.getUserPersistence();
    }

    public String login(String email, String password){
        User tempUser= userPersistence.getUserList().get(email);

        if(tempUser != null){
            if(tempUser.getPassword().equals(password)){
                return email;
            }
        }

        return null;
    }

    public boolean registerAccount(String email, String firstName, String lastName, String password, String rePassword){
        User tempUser= userPersistence.getUserList().get(email);

        //Email doesn't exist, can create account.
        if(tempUser== null){

            if(password.equals(rePassword)){
                userPersistence.addUser(email, new User(firstName, lastName, email, password));
                return true;
            }else {
                //Password do not match.
                return false;
            }
        }else {
            //The email is already exist.
            return false;
        }
    }
}
