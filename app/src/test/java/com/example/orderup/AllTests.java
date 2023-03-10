package com.example.orderup;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.orderup.Objects.RestaurantObjectTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RestaurantObjectTest.class
})
public class AllTests
{

}
