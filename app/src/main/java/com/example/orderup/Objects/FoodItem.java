package com.example.orderup.Objects;

public class FoodItem {

    private int restaurant_id;
    private int item_id;
    private final String item_name;
    private final double price;
    private final String image_url;
    private final String item_description;

    public FoodItem() {
        restaurant_id = 0;
        item_id = 0;
        item_name = "Default Name";
        item_description = "Default Description";
        price = 0;
        image_url = "";

    }

    public FoodItem(int restaurant_id, int item_id, String itemName, double itemPrice, String itemImageURL, String itemDescription ){
        this.restaurant_id= restaurant_id;
        item_id = item_id;
        item_name= itemName;
        price= itemPrice;
        image_url= itemImageURL;
        item_description= itemDescription;

    }
    public int getRestaurant_id(){
        return restaurant_id;
    }
    public int getItem_id(){
        return item_id;
    }

    public String getItemName(){
        return item_name;
    }
    public double getItemPrice(){
        return price;
    }
    public String getImageUrl(){
        return image_url;
    }
    public String getItemDescription(){
        return item_description;
    }
}
