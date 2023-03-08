package com.example.orderup.persistance.hsqldb;

import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

//public class UserPersistenceHSQLDB implements RestaurantPersistence  {
public class UserPersistenceHSQLDB implements UserPersistence {
    private final String dbPath;

    public UserPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }
    /*
    called in getRestaurantSequential()
    returns a Restaurant object
     */

    private User fromResultSet(final ResultSet rs) throws SQLException {

        final int id = rs.getInt("ID");
        final String email = rs.getString("EMAIL");
        final String password = rs.getString("PASSWORD");
        final String firstname = rs.getString("FIRSTNAME");
        final String lastname = rs.getString("LASTNAME");
        final String creditcard = rs.getString("CREDITCARD");
        final String csv = rs.getString("CSV");
        final String expiry = rs.getString("EXPIRY");
        final String address = rs.getString("ADDRESS");
        final String balance = rs.getString("BALANCE");

        return new User(id,email,password,firstname,lastname,creditcard,csv,expiry,address,balance);
    }

    /*
    method returns a list of Restaurant objects in the database
     */
    @Override
    public List<Restaurant> getUserSequential() {
        final List<Restaurant> restaurants = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM restaurants");
            while (rs.next()) {
                final Restaurant restaurant = fromResultSet(rs);
                restaurants.add(restaurant);
            }
            rs.close();
            st.close();

            return restaurants;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public HashMap<String, User> getUserList() {
        return null;
    }

    @Override
    public void addUser(String email, String firstName, String lastName, String password) {

    }

    @Override
    public void addCreditCard(String email, String cardNum, String cvc, String expiry) {

    }

    @Override
    public void updateFirstName(String email, String firstName) {

    }

    @Override
    public void updateLastName(String email, String lastName) {

    }

    @Override
    public void updatePassword(String email, String password) {

    }

    @Override
    public void updateAddress(String email, String address) {

    }

    @Override
    public void addBalance(String email, float balance) {

    }

    @Override
    public String getBalance(String email) {
        return null;
    }

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

    }
}
