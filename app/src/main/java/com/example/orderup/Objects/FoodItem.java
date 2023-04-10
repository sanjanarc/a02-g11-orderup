package com.example.orderup.Objects;

/**
 * This class holds a single FoodItem object.
 */
public class FoodItem {

    // Food item attributes.
    private int restaurant_id;
    private int item_id, numItems;
    private final String item_name;
    private final double price;
    private final String image_url;
    private final String item_description;

    /**
     * Constructor.
     */
    public FoodItem() {
        restaurant_id = 0;
        item_id = 0;
        item_name = "Default Name";
        item_description = "Default Description";
        price = 0;
        image_url = "";
        numItems = 1;
    }

    /**
     * Constructor.
     *
     * @param restaurant_id   the restaurant id.
     * @param item_id         the item id.
     * @param itemName        the item name.
     * @param itemPrice       the item price.
     * @param itemImageURL    the item image url.
     * @param itemDescription the item description.
     */
    public FoodItem(int restaurant_id, int item_id, String itemName, double itemPrice, String itemImageURL, String itemDescription) {
        this.restaurant_id = restaurant_id;
        this.item_id = item_id;
        item_name = itemName;
        price = itemPrice;
        image_url = itemImageURL;
        item_description = itemDescription;
        numItems = 1;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getItemName() {
        return item_name;
    }

    public double getItemPrice() {
        return price;
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getItemDescription() {
        return item_description;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int num) {
        this.numItems = num;
    }
}
