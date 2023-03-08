package com.example.orderup.persistance.hsqldb;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.Objects.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//public class UserPersistenceHSQLDB implements RestaurantPersistence  {
public class UserPersistenceHSQLDB  {
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
    called in fromResultSet() method
    returns FoodItem associated with specific restaurantID and fooditem ID
     */
    private FoodItem getFoodById(int id, int itemID) {
        try(final Connection c = connection();){
            final Statement state = c.createStatement();
            String query = String.format("SELECT * FROM FoodItem WHERE id = %d, item_id= %d", id, itemID);
            final ResultSet menurs = state.executeQuery(query);
            return fromMenuResultSet(menurs);
        }catch (final SQLException e){

        }
        return null;
    }
    /*
    called by getFoodByID() method
    returns FoodItem from specified query
     */
    private FoodItem fromMenuResultSet(final ResultSet rs) throws SQLException {
        //ID ,ITEM_ID,ITEM_NAME,ITEM_PRICE,ITEM_IMAGE_URL,ITEM_DESC
        final int rest_id= rs.getInt("ID");
        final int item_id= rs.getInt("ITEM_ID");
        final String item_name= rs.getString("ITEM_NAME");
        final double item_price= rs.getDouble("ITEM_PRICE");
        final String item_image_url= rs.getString("ITEM_IMAGE_URL");
        final String item_desc=rs.getString("ITEM_DESC");

        return new FoodItem(rest_id,item_id,item_name,item_price,item_image_url,item_desc);


    }
    /*
    method returns a list of Restaurant objects in the database
     */
    @Override
    public List<Restaurant> getRestaurantSequential() {
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
}
