package com.example.orderup.persistance.hsqldb;

import static org.junit.Assert.*;

import com.example.orderup.Objects.User;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.SQLException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.File;
import java.io.IOException;

import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.logic.UserServices;
import com.example.orderup.persistance.RestaurantPersistence;
import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.hsqldb.RestaurantPersistenceHSQLDB;
import com.example.orderup.persistance.hsqldb.UserPersistenceHSQLDB;
import com.example.orderup.utils.TestFilesUtil;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class UserPersistenceHSQLDBTest {
    private UserServices userServices;
    private UserPersistence userPersistence;

    @Before
    public void setUp() throws SQLException {
        //mock of Restaurant Persistence created
        userPersistence= mock(UserPersistence.class);
        userServices= new UserServices(userPersistence);
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