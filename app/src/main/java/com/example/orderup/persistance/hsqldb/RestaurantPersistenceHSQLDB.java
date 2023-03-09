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

    @Override
    public List<Restaurant> getRestaurantRandom(Restaurant currentRestaurant) {
        final List<Restaurant> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM RESTAURANTS WHERE restaurnatID = ?");
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

    @Override
    public Restaurant insertRestaurant(Restaurant currentRestaurant) {
        return null;
    }

    @Override
    public Restaurant updateRestaurant(Restaurant currentRestaurant) {
        return null;
    }

    @Override
    public void deleteRestaurant(Restaurant currentRestaurant) {

    }

    @Override
    public int getImg(Restaurant currentRestaurant) {
        return 0;
    }

}
