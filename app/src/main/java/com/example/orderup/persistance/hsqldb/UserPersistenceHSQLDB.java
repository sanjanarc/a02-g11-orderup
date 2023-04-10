package com.example.orderup.persistance.hsqldb;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.Giftcard;
import com.example.orderup.Objects.User;
import com.example.orderup.persistance.UserPersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will get access to database and return to logic layer.
 */
public class UserPersistenceHSQLDB implements UserPersistence {

    // The database path.
    private final String dbPath;

    /**
     * Constructor.
     *
     * @param dbPath the database path.
     */
    public UserPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    /**
     * Get the connection from program to database.
     *
     * @return connection the connection of the database.
     * @throws SQLException will throw exception when connection to the database failed.
     */
    private Connection connection() throws SQLException {

        String path = "jdbc:hsqldb:file:" + dbPath + ";shutdown=true";

        return DriverManager.getConnection(path, "SA", "");
    }

    /**
     * Get the user info from the input result set.
     *
     * @param rs the result of the sql query.
     * @return User object
     * @throws SQLException will throw sql exception if the result set is incorrect.
     */
    private User fromResultSet(final ResultSet rs) throws SQLException {

        //Getting data from the result set table.
        final String email = rs.getString("EMAIL");
        final String password = rs.getString("PASSWORD");
        final String firstname = rs.getString("FIRSTNAME");
        final String lastname = rs.getString("LASTNAME");
        final String creditcard = rs.getString("CREDITCARD");
        final String cvc = rs.getString("CVC");
        final String expiry = rs.getString("EXPIRY");
        final String address = rs.getString("ADDRESS");
        final String balance = rs.getString("BALANCE");
        final Boolean membership = rs.getBoolean("MEMBERSHIP");

        //Return a user object with filled data.
        return new User(email, password, firstname, lastname, creditcard, cvc, expiry, address, balance, membership);
    }

    /**
     * Get the user info from database.
     *
     * @param email the specific user email.
     * @return User object.
     */
    @Override
    public User getUser(String email) {

        User user = null;

        try (final Connection c = connection()) {

            final PreparedStatement st = c.prepareStatement("SELECT * FROM USERS WHERE EMAIL=?");
            st.setString(1, email);
            final ResultSet rs = st.executeQuery();

            if (rs.next()) { // Check if the result set is empty or not.

                user = fromResultSet(rs);

            }

            rs.close();
            st.close();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }

        return user;
    }

    /**
     * Add new user object to the table.
     *
     * @param email     the user email.
     * @param password  the user account password.
     * @param firstName the user first name.
     * @param lastName  the user last name.
     */
    @Override
    public void addUser(String email, String password, String firstName, String lastName) {

        try (Connection c = connection()) {

            PreparedStatement st = c.prepareStatement("INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, email);
            st.setString(2, password);
            st.setString(3, firstName);
            st.setString(4, lastName);
            st.setString(5, null);
            st.setString(6, null);
            st.setString(7, null);
            st.setString(8, null);
            st.setFloat(9, 0.00F);
            st.setBoolean(10, false);
            st.executeUpdate();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Add credit card info to user table.
     *
     * @param email   the user email.
     * @param cardNum the credit card number.
     * @param cvc     the credit card cvc.
     * @param expiry  the expiry date of the credit card.
     */
    @Override
    public void addCreditCard(String email, String cardNum, String cvc, String expiry) {

        try (Connection c = connection()) {

            PreparedStatement st = c.prepareStatement("UPDATE USERS SET CREDITCARD = ?, CVC = ?, EXPIRY = ? WHERE EMAIL = ?");
            st.setString(1, cardNum);
            st.setString(2, cvc);
            st.setString(3, expiry);
            st.setString(4, email);
            st.executeUpdate();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Add the given address to the database.
     *
     * @param email   the user email.
     * @param address the user address.
     */
    @Override
    public void updateAddress(String email, String address) {

        try (Connection c = connection()) {

            PreparedStatement ps = c.prepareStatement("UPDATE USERS SET ADDRESS = ? WHERE EMAIL = ?");
            ps.setString(1, address);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }


    /**
     * Adds food item to cart in the DB.script
     *
     * @param email    the user's email.
     * @param rest_id  the restaurant id of the food item.
     * @param food_id  the id of the food item.
     * @param quantity the amount of the food item added to cart.
     */
    @Override
    public void updateCart(String email, int rest_id, int food_id, int quantity) {

        int tempquantity = quantity;

        // Combine duplicate food object into one.
        for (int i = 0; i < getFoodCart(email).size(); i++) {

            if (getFoodCart(email).get(i).getItem_id() == food_id && getFoodCart(email).get(i).getRestaurant_id() == rest_id) {

                tempquantity += getFoodCart(email).get(i).getNumItems();
                removeFromCart(email, getFoodCart(email).get(i).getRestaurant_id(), getFoodCart(email).get(i).getItem_id(), getFoodCart(email).get(i).getNumItems());

            }
        }

        try (Connection c = connection()) {

            PreparedStatement ps = c.prepareStatement("INSERT INTO cart VALUES (?, ?, ?, ?)");
            ps.setString(1, email);
            ps.setInt(2, rest_id);
            ps.setInt(3, food_id);
            ps.setInt(4, tempquantity);
            ps.executeUpdate();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Removes food item from cart in the DB.
     *
     * @param email    the user's email.
     * @param rest_id  the restaurant id of the food item.
     * @param food_id  the id of the food item.
     * @param quantity the amount of the food item to remove from the cart.
     */
    @Override
    public void removeFromCart(String email, int rest_id, int food_id, int quantity) {

        try (Connection c = connection()) {

            PreparedStatement ps = c.prepareStatement("DELETE FROM CART WHERE EMAIL = ? AND ID = ? AND ITEM_ID = ? AND QUANTITY = ?");
            ps.setString(1, email);
            ps.setInt(2, rest_id);
            ps.setInt(3, food_id);
            ps.setInt(4, quantity);
            ps.executeUpdate();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Retrieves a list of food items in a user's cart.
     *
     * @param email the email address of the user
     * @return list of food items in the user's cart
     */
    @Override
    public List<FoodItem> getFoodCart(String email) {

        List<FoodItem> foodCart = new ArrayList<>();

        try (Connection c = connection()) {

            String query = "SELECT f.*, c.QUANTITY " +
                    "FROM CART c " +
                    "INNER JOIN FOODITEM f ON c.ID = f.ID AND c.ITEM_ID = f.ITEM_ID " +
                    "WHERE c.EMAIL = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int restId = rs.getInt("ID");
                int itemId = rs.getInt("ITEM_ID");
                String itemName = rs.getString("ITEM_NAME");
                double itemPrice = rs.getDouble("ITEM_PRICE");
                String itemImageUrl = rs.getString("ITEM_IMAGE_URL");
                String itemDesc = rs.getString("ITEM_DESC");
                int quantity = rs.getInt("QUANTITY");
                FoodItem food = new FoodItem(restId, itemId, itemName, itemPrice, itemImageUrl, itemDesc);
                food.setNumItems(quantity);
                foodCart.add(food);

            }

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }

        return foodCart;
    }

    /**
     * Clears all items from cart in the database.
     *
     * @param email the user's email.
     */
    public void clearCart(String email) {

        try (Connection c = connection()) {

            PreparedStatement ps = c.prepareStatement("DELETE FROM CART WHERE EMAIL = ?");
            ps.setString(1, email);
            ps.executeUpdate();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Add or Reduce the balance from database.
     *
     * @param email   the user email.
     * @param balance the amount to remove or add.
     */
    @Override
    public void modifyBalance(String email, float balance) {

        try (Connection c = connection()) {

            PreparedStatement ps = c.prepareStatement("UPDATE USERS SET BALANCE = BALANCE + ? WHERE EMAIL = ?");
            ps.setDouble(1, balance);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Read and store the gift card data from database.
     *
     * @return List of gift card object.
     */
    @Override
    public List getGiftCards() {

        List<Giftcard> cardList = new ArrayList<>();

        try (final Connection c = connection()) {

            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM GIFTCARD");

            while (rs.next()) {

                cardList.add(new Giftcard(rs.getString("NUMBER"), rs.getFloat("AMOUNT")));

            }

            rs.close();
            st.close();

        } catch (final SQLException e) {

            throw new PersistenceException(e);

        }

        return cardList;
    }

    /**
     * Change the membership status of the user.
     *
     * @param email the email of the user.
     */
    @Override
    public void setMembership(String email) {

        try (Connection c = connection()) {

            PreparedStatement ps = c.prepareStatement("UPDATE USERS SET MEMBERSHIP = ? WHERE EMAIL = ?");
            ps.setBoolean(1, true);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }
    public void getMembership(String email){

    }
}