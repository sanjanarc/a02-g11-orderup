package com.example.orderup.persistance.hsqldb;

import android.util.Log;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

//This class is the interface of the user database.
public class UserPersistenceHSQLDB implements UserPersistence
{
    private final String dbPath;

    //Store the given database path.
    public UserPersistenceHSQLDB(final String dbPath)
    {
        this.dbPath = dbPath;
    }

    //Ask the Device to load and run the database script.
    private Connection connection() throws SQLException
    {
        //Log.d("path",dbPath);
        String path ="jdbc:hsqldb:file:" + dbPath + ";shutdown=true";
        Log.d("Here------------->", "Now inside connection");
        Log.d("Here----->Let see what path-------->",path);
        return DriverManager.getConnection(path, "SA", "");
    }

    //Create each user object from the given user database table.
    private User fromResultSet(final ResultSet rs) throws SQLException
    {
        //Getting data from the table.
        final String email = rs.getString("EMAIL");
        final String password = rs.getString("PASSWORD");
        final String firstname = rs.getString("FIRSTNAME");
        final String lastname = rs.getString("LASTNAME");
        final String creditcard = rs.getString("CREDITCARD");
        final String cvc = rs.getString("CVC");
        final String expiry = rs.getString("EXPIRY");
        final String address = rs.getString("ADDRESS");
        final String balance = rs.getString("BALANCE");

        //Return a user object with filled data.
        return new User(email,password,firstname,lastname,creditcard,cvc,expiry,address,balance);
    }

    //Build a user hash map from database and return the user table.
    @Override
    public HashMap<String, User> getUserTable()
    {
        final HashMap<String, User> userList = new HashMap<>();

        try (final Connection c = connection())
        {
            Log.d("this","1 line");
            final Statement st = c.createStatement();
            Log.d("this","2 line");
            final ResultSet rs = st.executeQuery("SELECT * FROM USERS");
            Log.d("this","3 line");
            while (rs.next()) {
                User user = fromResultSet(rs);
                userList.put(user.getEmail(), user);
            }
            Log.d("this","4 line");
            rs.close();
            st.close();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }

        return userList;
    }

    //Add new user object to the table.
    @Override
    public void addUser(String email, String password, String firstName, String lastName)
    {
        try(Connection c = connection())
        {
            Statement st = c.createStatement();
            st.executeQuery("INSERT INTO USERS VALUES (" + email + "," + password + "," + firstName + "," + lastName + "," + null + "," + null + "," + null + "," + null + "," + null + ")");
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    //Add credit card info to user table.
    @Override
    public void addCreditCard(String email, String cardNum, String cvc, String expiry)
    {
        try(Connection c = connection())
        {
            Statement st = c.createStatement();
            st.executeQuery("UPDATE USERS SET CREDITCARD = " + cardNum + ", CVC = " + cvc + ", EXPIRY = " + expiry + " WHERE EMAIL = " + email);
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    //Renaming the user.
    @Override
    public void updateFirstName(String email, String firstName)
    {
        try(Connection c = connection())
        {
            Statement st = c.createStatement();
            st.executeQuery("UPDATE USERS SET FIRSTNAME = " + firstName + " WHERE EMAIL = " + email);
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    //Renaming the user.
    @Override
    public void updateLastName(String email, String lastName)
    {
        try(Connection c = connection())
        {
            Statement st = c.createStatement();
            st.executeQuery("UPDATE USERS SET LASTNAME = " + lastName + " WHERE EMAIL = " + email);
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    //Reset the user password.
    @Override
    public void updatePassword(String email, String password)
    {
        try(Connection c = connection())
        {
            Statement st = c.createStatement();
            st.executeQuery("UPDATE USERS SET PASSWORD = " + password + " WHERE EMAIL = " + email);
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void updateAddress(String email, String address)
    {
        try(Connection c = connection())
        {
            Statement st = c.createStatement();
            st.executeQuery("UPDATE USERS SET ADDRESS = " + address + " WHERE EMAIL = " + email);
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void modifyBalance(String email, float balance)
    {
        try(Connection c = connection())
        {
            Statement st = c.createStatement();
            st.executeQuery("UPDATE USERS SET BALANCE = " + balance + " WHERE EMAIL = " + email);
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }
    /* Do we really need these method?
    @Override
    public User insertUser(User currentUser) {
        return null;
    }

    @Override
    public User updateUser(User currentUser) {
        return null;
    }

    @Override
    public void deleteUser(User currentUser) {

    }*/
}
