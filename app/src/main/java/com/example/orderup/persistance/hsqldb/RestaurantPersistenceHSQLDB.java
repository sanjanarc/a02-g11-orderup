package com.example.orderup.persistance.hsqldb;

import static java.security.AccessController.getContext;

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
import com.example.orderup.logic.Services;
import com.example.orderup.persistance.RestaurantPersistence;

public class RestaurantPersistenceHSQLDB implements RestaurantPersistence{

    private final String dbPath;

    public RestaurantPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }


    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    /*
  called in getRestaurantSequential()
  returns a Restaurant object
   */
    private Restaurant fromResultSet(final ResultSet rs) throws SQLException {
        final int id = rs.getInt("ID");
        final String name = rs.getString("NAME");
        final String category = rs.getString("CATEGORY");
        final String city = rs.getString("CITY");
        final String description = rs.getString("DESCRIPTION");
//        final int num_ratings = rs.getInt("NUM_RATINGS");
//        final int average_rating = rs.getInt("AVERAGE_RATING");
        final String image = rs.getString("IMAGE");
        //final int location = rs.getArray("LOCATION"); //idk why this is an error// Hence, only using 1 location for now
        final String location= rs.getString("LOCATION");

        System.out.println("before food1");
        final FoodItem item1 = getFoodById(id,1); //get fooditem in the rest's menu
        System.out.println("after food1");
//        Log.d("thid", item1.getItemDescription());
        final FoodItem item2 = getFoodById(id,2);
        final FoodItem item3 = getFoodById(id,3);

        return new Restaurant(id,name,category,city,description,item1,item2, item3,location,image);
    }


    /*
   called in fromResultSet() method
   returns FoodItem associated with specific restaurantID and fooditem ID
    */
    private FoodItem getFoodById(int id, int itemID) {

        try (final Connection c = connection()) {
            String query = "SELECT * FROM FOODITEM WHERE ITEM_ID = ?";
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setInt(1, itemID);
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

    public int get_image() throws SQLException {
        Connection conn = connection();
// Query the database for the resource ID of the image
        PreparedStatement stmt = conn.prepareStatement("SELECT IMAGE FROM RESTAURANTS WHERE id = ?");
        stmt.setInt(1, 1);
        ResultSet rs = stmt.executeQuery();
        int resourceId = 0;
        if (rs.next()) {
            resourceId = rs.getInt("resource_id");
        }
        rs.close();
        stmt.close();
        return resourceId;
    }
    public boolean insertRestaurant(Restaurant currentRestaurant) {
        return false;
    }


    public boolean findRestaurant(int restNum) {
        return false;
    }

    public void updateRestaurant(Restaurant currentRestaurant) {

    }
    public void deleteRestaurant(int currentRestaurantNum) {

    }


}
