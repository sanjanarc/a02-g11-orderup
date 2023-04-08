package com.example.orderup.persistance.hsqldb;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.persistance.RestaurantPersistence;

/**
 * This class implements the restaurant database using HSQLDB
 */
public class RestaurantPersistenceHSQLDB implements RestaurantPersistence{

    private final String dbPath;

    /**
     * Constructor.
     *
     * @param dbPath the string containing the file path for the database.
     */
    public RestaurantPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    /**
     * Get the connection from program to database.
     *
     * @return connection the connection of the database.
     * @throws SQLException will throw exception when connection to the database failed.
     */
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    /**
     * Get the user info from the input result set.
     *
     * @param rs the result of the sql query.
     * @return Restaurant object
     * @throws SQLException will throw sql exception if the result set is incorrect.
     */
    private Restaurant fromResultSet(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("ID");
        final String name = rs.getString("NAME");
        final String category = rs.getString("CATEGORY");
        final String city = rs.getString("CITY");
        final String description = rs.getString("DESCRIPTION");
        final String image = rs.getString("IMAGE");
        final String location= rs.getString("LOCATION");
        final int num_items= rs.getInt("NUM_ITEMS");
        final FoodItem item1 = getFoodById(id,1); //get fooditem in the rest's menu
        final FoodItem item2 = getFoodById(id,2);
        final FoodItem item3 = getFoodById(id,3);

        return new Restaurant(id,name,category,city,description,item1,item2, item3,num_items,location,image);
    }

    /**
     * Get the specific restaurant by passing the restaurant id.
     *
     * @param id the restaurant id.
     * @return Restaurant object.
     */
    public Restaurant getRest(int id) {

        Restaurant restaurant = null;

        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM RESTAURANTS WHERE id = ?");
            st.setInt(1, id);
            final ResultSet rs = st.executeQuery();

            if (rs.next()) {
                restaurant = fromResultSet(rs);
            }

            rs.close();
            st.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return restaurant;
    }

    /**
     * Gets a food item from the food item database by ID
     *
     * @param id the restaurant ID.
     * @param itemID the food item ID.
     * @return FoodItem associated with specific restaurantID and fooditem ID.
     */
    private FoodItem getFoodById(int id, int itemID) {

        try (final Connection c = connection()) {
            String query = "SELECT * FROM FOODITEM WHERE ID = ? AND ITEM_ID = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.setInt(2, itemID);
            ResultSet menurs = pstmt.executeQuery();
            menurs.next();
            return fromMenuResultSet(menurs);
        } catch (final SQLException e) {

        }
        return null;
    }

    /*
    called by getFoodByID() method
    returns FoodItem from specified query
     */

    /**
     * Gets a FoodItem from the database
     *
     * @param rs the result of the sql query.
     * @return FoodItem from specified query.
     * @throws SQLException will throw sql exception if the result set is incorrect.
     */
    private FoodItem fromMenuResultSet(final ResultSet rs) throws SQLException {

        final int rest_id= rs.getInt("ID");
        final int item_id= rs.getInt("ITEM_ID");
        final String item_name= rs.getString("ITEM_NAME");
        final double item_price= rs.getDouble("ITEM_PRICE");
        final String item_image_url= rs.getString("ITEM_IMAGE_URL");
        final String item_desc=rs.getString("ITEM_DESC");

        return new FoodItem(rest_id,item_id,item_name,item_price,item_image_url,item_desc);
    }

    /**
     * Aggregates a list of all comments left on a restaurant given it's ID
     *
     * @param restaurantID the ID of the restaurant.
     * @return a list of comments left of the restaurant that has the restaurant id.
     */
    public List<String> getComments(int restaurantID){
        final List<String> comments = new ArrayList<>();

        try (final Connection c = connection()) {
            String query = "SELECT * FROM comments WHERE ID = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, restaurantID); //restaurant of specified id
            ResultSet commentRS = pstmt.executeQuery();
            while(commentRS.next()) {
                comments.add(commentRS.getString("COMMENT"));
            }
            return comments; //return the list of comments
        } catch (final SQLException e) {

        }
        return null;
    }

    /**
     * Aggregates a list of all restaurants.
     *
     * @return a list of Restaurant objects in the database.
     */
    public List<Restaurant> getRestaurantSequential() {
        final List<Restaurant> restaurants = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM RESTAURANTS");
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

    /**
     * Adds a comment left on a restaurant's place to the DB.script
     *
     * @param restaurantID the ID of the restaurant.
     * @param comment the comment to be left on the restaurant.
     */
    public void insertComment(int restaurantID, String comment)
    {
        try(Connection c = connection())
        {
            PreparedStatement st = c.prepareStatement("INSERT INTO comments VALUES (?, ?)");
            st.setInt(1, restaurantID);
            st.setString(2, comment);

            st.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new PersistenceException(e);
        }
    }
}
