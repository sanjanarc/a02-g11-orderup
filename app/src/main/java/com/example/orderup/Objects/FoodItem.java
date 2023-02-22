package com.example.orderup.Objects;

public class FoodItem {

    private final String item_name;
    private final double price;
    private final String image_url;
    private final String item_description;


    public FoodItem(String itemName, double itemPrice, String itemImageURL, String itemDescription ){

        item_name= itemName;
        price= itemPrice;
        image_url= itemImageURL;
        item_description= itemDescription;

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
