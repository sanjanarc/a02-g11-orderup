package com.example.orderup.persistance.hsqldb.IntegrationTests;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.File;
import java.io.IOException;

import com.example.orderup.logic.UserServices;
import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.hsqldb.RestaurantPersistenceHSQLDB;
import com.example.orderup.persistance.hsqldb.UserPersistenceHSQLDB;
import com.example.orderup.utils.TestFilesUtil;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserIntegrationTest {
    private UserServices userServices;
    private UserPersistence userPersistence;
    private File tempDB;
    @Before
    public void setUp() throws IOException{
        this.tempDB= TestFilesUtil.copyDB();
        this.userPersistence= new UserPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        this.userServices= new UserServices(userPersistence);
    }

    @Test
    public void testGetUser(){
        String email= "admin2@email.com";
        User user;
        user= userServices.getUser(email);
        assertNotNull("User object should not be null", user);
        assertEquals("User's first name should be the same as the name in persistence","John", user.getFirstName());
        assertEquals("User's last name should be the same as the name in persistence", "Doe", user.getLastName());
        assertEquals("User's address should be the same as the one in persistence", "66 Chancellors Circle, Winnipeg, Manitoba, R3T2N2", user.getAddress());
        assertEquals("This user's balance should be 0.0", 0.0F, user.getBalance());

    }
    @Test
    public void createUser(){
        
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}
