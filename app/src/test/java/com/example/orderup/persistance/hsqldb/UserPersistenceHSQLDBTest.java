package com.example.orderup.persistance.hsqldb;

import static org.junit.Assert.*;

import com.example.orderup.Objects.User;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class UserPersistenceHSQLDBTest {
    Connection connection = null;
    String dbPath;

    @Before
    public void connection(){
        setDbTest("com/example/orderup/persistance/hsqldb");
        try {
            connection = DriverManager.getConnection(dbPath, "SA", "");
        }catch (SQLException e){
            System.out.println("Connection is not working. ---->");
            e.printStackTrace();
        }
    }

    private void setDbTest(String name){
        try
        {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        dbPath = name;
    }

    @Test
    public void getUserTable() {
        HashMap<String, User> userTable = new HashMap<>();

    }

    @Test
    public void addUser() {

    }

    @Test
    public void addCreditCard() {
    }

    @Test
    public void updateFirstName() {
    }

    @Test
    public void updateLastName() {
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void updateAddress() {
    }

    @Test
    public void modifyBalance() {
    }

    @Test
    public void getGiftcards() {
    }
}