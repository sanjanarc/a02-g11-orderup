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
        final String image = rs.getString("IMAGE");
        final String location= rs.getString("LOCATION");
        final int num_items= rs.getInt("NUM_ITEMS");
        System.out.println("before food1");
        final FoodItem item1 = getFoodById(id,1); //get fooditem in the rest's menu
        System.out.println("after food1");
        final FoodItem item2 = getFoodById(id,2);
        final FoodItem item3 = getFoodById(id,3);

        return new Restaurant(id,name,category,city,description,item1,item2, item3,num_items,location,image);
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


    /*
    Methods below are for testing:
     */

    /*
    Insert a restaurant to the database along with its menu
     */
    public boolean insertRestaurant(Restaurant currentRestaurant) {
        try (final Connection c = connection();){
            PreparedStatement st = c.prepareStatement("INSERT INTO RESTAURANTS VALUES(?,?,?,?,?,?,?,?)");
            st.setInt(1, currentRestaurant.getRestaurantID());
            st.setString(2, currentRestaurant.getRestaurantName());
            st.setString(3, currentRestaurant.getRestaurantCategory());
            st.setString(4, currentRestaurant.getCityName());
            st.setString(5, currentRestaurant.getRestaurantDescription());
            st.setInt(6, currentRestaurant.getNum_menuItem());
            st.setString(7, currentRestaurant.getRestaurant_location());
            st.setString(8,currentRestaurant.getImagesURL());
            st.executeUpdate();

            PreparedStatement mt= c.prepareStatement("INSERT INTO FOODITEM VALUES(?, ?, ?, ?, ?, ?)");
            //add food item 1
            mt.setInt(1,currentRestaurant.getRestaurantID());
            mt.setInt(2,currentRestaurant.getItem1().getItem_id());
            mt.setString(3,currentRestaurant.getItem1().getItemName());
            mt.setDouble(4, currentRestaurant.getItem1().getItemPrice());
            mt.setString(5, currentRestaurant.getItem1().getImageUrl());
            mt.setString(6,currentRestaurant.getItem1().getItemDescription());
            mt.executeUpdate();

            //add food item 2
            mt.setInt(1,currentRestaurant.getRestaurantID());
            mt.setInt(2,currentRestaurant.getItem2().getItem_id());
            mt.setString(3,currentRestaurant.getItem2().getItemName());
            mt.setDouble(4, currentRestaurant.getItem2().getItemPrice());
            mt.setString(5, currentRestaurant.getItem2().getImageUrl());
            mt.setString(6,currentRestaurant.getItem2().getItemDescription());
            mt.executeUpdate();

            //add food item 3
            mt.setInt(1,currentRestaurant.getRestaurantID());
            mt.setInt(2,currentRestaurant.getItem3().getItem_id());
            mt.setString(3,currentRestaurant.getItem3().getItemName());
            mt.setDouble(4, currentRestaurant.getItem3().getItemPrice());
            mt.setString(5, currentRestaurant.getItem3().getImageUrl());
            mt.setString(6,currentRestaurant.getItem3().getItemDescription());
            mt.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /*
    Method used to Find a restaurant by its restaurant ID passed as parameter
     */
    public boolean findRestaurant(int restNum) {
        boolean restaurantFound=false;
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM RESTAURANT WHERE ID=?");
            st.setInt(1, restNum);
            final ResultSet rs = st.executeQuery();
            Restaurant rest;
            if (rs.next()) {
                rest = fromResultSet(rs);
            } else {
                rest = null;
            }
            rs.close();
            st.close();
            if(rest!=null){
                restaurantFound= true;
            }
        }
        catch (SQLException e) {
            throw new PersistenceException(e);
        }
        return restaurantFound;



    }

    /*
    Method updates properties of a restaurant
     */
    public void updateRestaurant(Restaurant currentRestaurant) {
        try (final Connection c = connection()) {

            final PreparedStatement st = c.prepareStatement("UPDATE RESTAURANTS SET ID = ?, NAME = ?,CATEGORY = ?, CITY = ?,DESCRIPTION = ?, NUM_ITEMS = ?, LOCATION= ?, IMAGES= ?, WHERE prodID = ?");
            st.setInt(1, currentRestaurant.getRestaurantID());
            st.setString(2, currentRestaurant.getRestaurantName());
            st.setString(3, currentRestaurant.getRestaurantCategory());
            st.setString(4, currentRestaurant.getCityName());
            st.setString(5, currentRestaurant.getRestaurantDescription());
            st.setInt(6, currentRestaurant.getNum_menuItem());
            st.setString(7, currentRestaurant.getRestaurant_location());
            st.setInt(9, currentRestaurant.getRestaurantID());

            st.executeUpdate();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }



    }
    public void deleteRestaurant(int currentRestaurantNum) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM RESTAURANTS WHERE ID = ?");
            st.setInt(1, currentRestaurantNum);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

    }


}
