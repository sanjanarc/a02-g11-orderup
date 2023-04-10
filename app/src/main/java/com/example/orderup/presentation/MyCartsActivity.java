package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.Restaurant;
import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.RestaurantServices;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.List;

/**
 * The cart page UI class.
 */
public class MyCartsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_carts);

        UserServices userServices = new UserServices(Services.getUserPersistence());
        User user = userServices.getUser(Services.getCurrentUser());

        // Display the food list.
        updateCartInfo(user.getFoodCart());

        // Bottom event listener.
        MaterialButtonToggleGroup toggleGroup = findViewById(R.id.toggleGroup);
        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {

                // Detect which delivery pattern does the user choose.
                if (isChecked && checkedId == R.id.deliveryButton) {

                    if (user.getAddress() == null) {

                        ErrorPopUp.errorMsg(MyCartsActivity.this, "No address found. Please add an address to your account");

                    } else {

                        ErrorPopUp.errorMsg(MyCartsActivity.this, "Order will be delivered at " + user.getAddress());

                    }

                } else if (isChecked && checkedId == R.id.pickupButton) {

                    // Get the food info.
                    int id;
                    RestaurantServices restaurantServices = new RestaurantServices(Services.getRestaurantPersistence());
                    Restaurant restaurant;
                    FoodItem foodItem;
                    String RestaurantAddress = "";
                    List<FoodItem> foodItemList = user.getFoodCart();

                    // Loop through the food list and get all the restaurant address.
                    for (int i = 0; i < user.getFoodCart().size(); i++) {

                        foodItem = foodItemList.get(i);
                        id = foodItem.getRestaurant_id();
                        restaurant = restaurantServices.getRest(id);
                        RestaurantAddress += restaurant.getRestaurant_location() + "\n";

                    }

                    // Display address to user.
                    ErrorPopUp.errorMsg(MyCartsActivity.this, "You can pick up your order at \n" + RestaurantAddress);
                }
            }
        });

        // Get reference from .xml file.
        TextView subTotalTextView = findViewById(R.id.SubTotal);
        TextView DeliveryFeeView = findViewById(R.id.Delivery);
        TextView TaxView = findViewById(R.id.Tax);

        double subTotal = 0.00;
        double deliveryFee = 0.00;
        FoodItem foodItem;

        // Get subtotal
        for (int i = 0; i < user.getFoodCart().size(); i++) {

            foodItem = user.getFoodCart().get(i);
            double price = foodItem.getNumItems() * foodItem.getItemPrice();
            subTotal += price;

        }

        // Check if user gets membership discount
        if (user.getFoodCart().size() != 0) {

            if (user.getMembership()) {

                deliveryFee = 2.99;

            } else {

                deliveryFee = 3.60;

            }
        }

        double tax = subTotal * 0.07;
        double total = subTotal + deliveryFee + tax;

        // Set the text of the SubTotal TextView to the value of the subTotal variable
        subTotalTextView.setText(String.format("SubTotal             $%.2f", subTotal));
        DeliveryFeeView.setText(String.format("Delivery Fee         $%.2f", deliveryFee));
        TaxView.setText(String.format("Tax                         $%.2f", tax));

        // Continue Button event listener.
        Button ContinueButton = (Button) findViewById(R.id.toPaymentButton);
        ContinueButton.setText(String.format("Continue $%.2f", total));
        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user.getFoodCart().size() == 0) {

                    // Display to user that cart is already empty.
                    ErrorPopUp.errorMsg(MyCartsActivity.this, "Cart is empty");

                } else {

                    // Go to the checkout page.
                    Intent intent = new Intent(getBaseContext(), CheckoutActivity.class);
                    startActivity(intent);

                    // Re
                    finish();

                }
            }
        });
    }

    /**
     * Display the food list to cart page.
     *
     * @param list a list of food item.
     */
    public void updateCartInfo(List list) {

        // Display the food list.
        RecyclerView recyclerView = findViewById(R.id.cartViewRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyCartAdapter(list));

    }
}