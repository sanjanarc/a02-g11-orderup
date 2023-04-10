package com.example.orderup.persistance.hsqldb;

import static org.junit.Assert.*;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.Objects.User;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.orderup.logic.UserServices;
import com.example.orderup.logic.UserVerification;
import com.example.orderup.persistance.UserPersistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.util.Log;


public class UserPersistenceHSQLDBTest {
    private UserServices userServices;
    private UserPersistence userPersistence;
    private UserVerification userVerification;

    @Before
    public void setUp() throws SQLException {
        //mock of Restaurant Persistence created
        userPersistence= Mockito.mock(UserPersistence.class);
        userServices= new UserServices(userPersistence);
        userVerification= new UserVerification(userPersistence);

    }

    @Test
    public void addUser() throws Exception {

        // Set up the class under test with the mocked connection
        final User user;
        User retrievedUser;
        System.out.println("\nStarting test ");
        final List<User> users = new ArrayList<>();
        final String email = "johndoe@example.com";
        final String password = "password";
        final String firstName = "John";
        final String lastName = "Doe";
        user = new User(firstName, lastName,email, password);

        when(userPersistence.getUser(email)).thenReturn(user);
        retrievedUser = userServices.getUser(email);

        // Verify that the user was added to the database
        assertNotNull(retrievedUser);
        assertEquals(email, retrievedUser.getEmail());
        assertEquals(password, retrievedUser.getPassword());
        assertEquals(firstName, retrievedUser.getFirstName());
        assertEquals(lastName, retrievedUser.getLastName());
        assertEquals("", retrievedUser.getCreditCard());
        assertEquals("", retrievedUser.getExpiry());
        assertEquals(null, retrievedUser.getAddress());
        assertEquals(0.00F, retrievedUser.getBalance(), 0.001);
    }


    @Test
    public void updateFirstName() {
        final String email = "johndoe@example.com";
        final String password = "password";
        final String firstName = "John";
        final String lastName = "Doe";
        userPersistence.addUser(email,password,firstName,lastName);
        User user = new User(firstName, lastName,email, password);

        when(userPersistence.getUser(email)).thenReturn(user);
        User retrievedUser= userServices.getUser(email);
        assertEquals("John",retrievedUser.getFirstName());

    }

    @Test
    public void testMembership() {
        final String email = "johndoe@example.com";
        final String password = "password";
        final String firstName = "John";
        final String lastName = "Doe";

        User user = new User(firstName, lastName,email, password);
        userPersistence.addUser(email,password,firstName,lastName);
        when(userPersistence.getUser(email)).thenReturn(user);
        User retrievedUser= userPersistence.getUser(email);
        assertTrue(!retrievedUser.getMembership()); //user should not be a member
    }

}