package com.example.orderup.logic;

import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.persistance.stub.UserPersistenceStub;

/*  Don't know what this class really, just copy and paste from the sample project.
*   Maybe this class is used to prevent multiple userPersistence object.
* */
public class Services
{
    private static UserPersistence userPersistence= null;

    public static synchronized UserPersistence getUserPersistence()
    {
        if(userPersistence== null)
        {
            userPersistence= new UserPersistenceStub();
        }
        return userPersistence;
    }
}
