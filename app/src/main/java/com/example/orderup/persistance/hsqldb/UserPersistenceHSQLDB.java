package com.example.orderup.persistance.hsqldb;

import android.util.Log;

import com.example.orderup.Objects.Giftcard;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

        String path ="jdbc:hsqldb:file:" + dbPath + ";shutdown=true";
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
            PreparedStatement st = c.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, email);
            st.setString(2, password);
            st.setString(3, firstName);
            st.setString(4, lastName);
            st.setString(5, null);
            st.setString(6, null);
            st.setString(7, null);
            st.setString(8, null);
            st.setFloat(9, 0.00F);
            st.executeUpdate();
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
            PreparedStatement st = c.prepareStatement("UPDATE USERS SET CREDITCARD = ?, CVC = ?, EXPIRY = ? WHERE EMAIL = ?");
            st.setString(1, cardNum);
            st.setString(2, cvc);
            st.setString(3, expiry);
            st.setString(4, email);
            st.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    //Add the given address to the database.
    @Override
    public void updateAddress(String email, String address)
    {
        try(Connection c = connection())
        {
            PreparedStatement ps = c.prepareStatement("UPDATE USERS SET ADDRESS = ? WHERE EMAIL = ?");
            ps.setString(1, address);
            ps.setString(2, email);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    //Add or Reduce the balance from database.
    @Override
    public void modifyBalance(String email, float balance)
    {
        try(Connection c = connection())
        {
            PreparedStatement ps = c.prepareStatement("UPDATE USERS SET BALANCE = BALANCE + ? WHERE EMAIL = ?");
            ps.setDouble(1, balance);
            ps.setString(2, email);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }

    //Read and store the giftcard data frim database.
    @Override
    public Giftcard[] getGiftcards() {
        Giftcard cardList[] = new Giftcard[5];
        int i = 0;
        try (final Connection c = connection())
        {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM GIFTCARD");
            while (rs.next()) {
                cardList[i] = new Giftcard(rs.getString("NUMBER"), rs.getFloat("AMOUNT"));
                i++;
            }
            rs.close();
            st.close();
        }
        catch (final SQLException e)
        {
            throw new PersistenceException(e);
        }

        return cardList;
    }
}
