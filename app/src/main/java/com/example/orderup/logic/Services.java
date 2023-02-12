package com.example.orderup.logic;

import com.example.orderup.persistance.UserPersistence;
import com.example.orderup.persistance.stub.UserPersistenceStub;

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
