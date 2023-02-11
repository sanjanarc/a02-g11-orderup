package com.example.orderup.persistance.stub;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.util.HashMap;

public class UserPersistenceStub implements UserPersistence {

    private HashMap<String, User> userList;

    public UserPersistenceStub(){
        this.userList= new HashMap<String, User>();

        userList.put("admin@email.com", new User("admin", "admin", "admin@email.com", "admin"));
        userList.put("GaryChalmers@email.com", new User("Gary", "Chalmers", "GaryChalmers@email.com", "GaryChalmers"));
        userList.put("SelmaBouvier@email.com", new User("Selma", "Bouvier", "SelmaBouvier@email.com", "SelmaBouvier"));
        userList.put("ArniePye@email.com", new User("Arnie", "Pye", "ArniePye@email.com", "ArniePye"));
        userList.put("MaryBailey@email.com", new User("Mary", "Bailey", "MaryBailey@email.com", "MaryBailey"));
        //add more if needed.
    }

    @Override
    public HashMap getUserList(){
        return userList;
    }

    @Override
    public void addUser(String email, User newUser){
        userList.put(email, newUser);
    }

    @Override
    public void addCreditCard(String email, String cardNum, String cvc, String expiry){
        userList.get(email).addCreditCard(cardNum, cvc, expiry);
    }

    @Override
    public void updateFirstName(String email, String firstName){
        userList.get(email).updateFirstName(firstName);
    }

    @Override
    public void updateLastName(String email, String lastName){
        userList.get(email).updateLastName(lastName);
    }

    @Override
    public void updatePassword(String email, String password){
        userList.get(email).updatePass(password);
    }

    @Override
    public void updateAddress(String email, String address){
        userList.get(email).updateAddress(address);
    }
}
