package com.example.orderup.persistance.hsqldb;

import com.example.orderup.logic.Services;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;

public class RestaurantPersistenceHSQLDBTest {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {
        // Mock the database connection, statement, and result set
        connection = mock(Connection.class);
        statement = mock(Statement.class);
        resultSet = mock(ResultSet.class);

        // Set up the mock to return data when executed
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery("SELECT * FROM RESTAURANTS")).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(1, 2);
        when(resultSet.getString("name")).thenReturn("Restaurant 1", "Restaurant 2");
        when(resultSet.getString("address")).thenReturn("123 Main St", "456 Maple Ave");
        // add more when statements to return other columns as needed

        // Any other setup you need to do
    }

    @Test
    public void testGetRestaurantSequential() throws SQLException {
        // Set up the class under test with the mocked connection
        RestaurantPersistenceHSQLDB dao = new RestaurantPersistenceHSQLDB(Services.getDBPathName());

        // Call the method being tested
        List<Restaurant> restaurants = dao.getRestaurantSequential();

        // Verify the results
        assertEquals(2, restaurants.size());
        assertEquals("Restaurant 1", restaurants.get(0).getRestaurantName());
        assertEquals("123 Main St", restaurants.get(0).getRestaurant_location());
        assertEquals("Restaurant 2", restaurants.get(1).getRestaurantName());
        assertEquals("456 Maple Ave", restaurants.get(1).getRestaurant_location());
        // add more assertions for other columns as needed
    }

    private Connection c;

    public int id = 1;
    public String name = "R1";
    public String category = "C1";
    public String city = "W";
    public String description = "Desc";
    public String item1 = null;
    public String item2 = null;
    public String item3 = null;
    public int num_items = 3;
    public String location = "Location";
    public String image = "image";
    @Test
    public void testRestaurantInsertion() {
        RestaurantPersistence restaurantPersistence = new RestaurantPersistenceHSQLDB(Services.getDBPathName());
        Assert.assertNotNull("Successful datapath established.", restaurantPersistence);
    }


}
