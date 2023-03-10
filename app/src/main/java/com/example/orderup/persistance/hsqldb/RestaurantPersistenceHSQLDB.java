package com.example.orderup.persistance.hsqldb;

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
        final int num_ratings = rs.getInt("NUM_RATINGS");
        final int average_rating = rs.getInt("AVERAGE_RATING");
        final int num_items = rs.getInt("NUM_ITEMS");
        //final int location = rs.getArray("LOCATION"); //idk why this is an error// Hence, only using 1 location for now
        final String location= rs.getString("LOCATION");

        final FoodItem item1 = getFoodById(id,1); //get fooditem in the rest's menu
        final FoodItem item2 = getFoodById(id,2);
        final FoodItem item3 = getFoodById(id,3);

        return new Restaurant(id,name,category,city,description,num_ratings,average_rating,item1,item2, item3, num_items,location);
    }


    /*
   called in fromResultSet() method
   returns FoodItem associated with specific restaurantID and fooditem ID
    */
    private FoodItem getFoodById(int id, int itemID) {
        try (final Connection c = connection();) {
            final Statement state = c.createStatement();
            String query = String.format("SELECT * FROM FoodItem WHERE id= %d, item_id= %d", id, itemID);
            final ResultSet menurs = state.executeQuery(query);
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
    @Override
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


    /*
    private Restaurant fromResultSet(final ResultSet rs) throws SQLException {
        final String rsRestaurantID = rs.getString("ID");
        final String rsRestaurantName = rs.getString("NAME");
        final String rsRestaurantCategory = rs.getString("CATEGORY");
        final String rsRestaurantCity = rs.getString("CITY");
        final String rsRestaurantDescription = rs.getString("DESCRIPTION");
        final int rsRestaurantNUMRating = rs.getInt("NUM_RATING");
        final int rsRestaurantAVGRATING = rs.getInt("AVERAGE_RATING");
        final int rsRestaurantNUMITEMS = rs.getInt("NUM_ITEMS");
        final String rsRestaurantLOCATION = rs.getString("LOCATION");
        final int rsRestaurantImage = rs.getInt("IMAGE");

        final String FoodItemName = rs.getString("ITEM_NAME");
        final int FoodItemPrice = rs.getInt("ITEM_PRICE");
        final String FoodItemImage = rs.getString("ITEM_IMAGE_URL");
        final String FoodItemDescription = rs.getString("ITEM_DESC");

        final FoodItem Food = new FoodItem(FoodItemName,FoodItemPrice,FoodItemImage,FoodItemDescription);
        final FoodItem Food1 = new FoodItem(FoodItemName,FoodItemPrice,FoodItemImage,FoodItemDescription);
        final FoodItem Food2 = new FoodItem(FoodItemName,FoodItemPrice,FoodItemImage,FoodItemDescription);

        return new Restaurant(rsRestaurantID,rsRestaurantName,rsRestaurantCategory,
                rsRestaurantDescription,rsRestaurantNUMRating, rsRestaurantAVGRATING,
                rsRestaurantImage,Food,Food1,Food2,rsRestaurantNUMITEMS,
                rsRestaurantLOCATION);

    }

    /*
    @Override
    public List<Restaurant> getRestaurantRandom(Restaurant currentRestaurant) {
        final List<Restaurant> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM RESTAURANTS WHERE restaurantID = ?");
            st.setString(1, currentRestaurant.getRestaurantID());

            final ResultSet rs = st.executeQuery();
            while(rs.next()) {
                final Restaurant restaurant = fromResultSet(rs);
                students.add(restaurant);
            }

            rs.close();
            st.close();

            return students;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }

     */




}
