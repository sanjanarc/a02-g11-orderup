package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.security.AccessController;
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

        MaterialButtonToggleGroup toggleGroup = findViewById(R.id.toggleGroup);



        toggleGroup.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener()  {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (isChecked && checkedId == R.id.deliveryButton) {
                    if(user.getAddress() == null) {
                        ErrorPopUp.errorMsg(MyCartsActivity.this, "No address found. Please add an address to your account");
                    }else {
                        ErrorPopUp.errorMsg(MyCartsActivity.this, "Order will be delivered at  " + user.getAddress());
                    }
                } else if (isChecked && checkedId == R.id.pickupButton) {

                    String RestaurantAddress = "";

                    for(int i=0; i<user.getFoodCart().size();i++) {
                        FoodItem food = user.getFoodCart().get(i);
                        int id = food.getRestaurant_id();
                        RestaurantServices restaurantServices = new RestaurantServices(Services.getRestaurantPersistence());
                        Restaurant rest = restaurantServices.getRest(id);
                        RestaurantAddress += rest.getRestaurant_location() + "\n";
                    }

                    ErrorPopUp.errorMsg(MyCartsActivity.this, "You can pick up your order at" + RestaurantAddress );
                }
            }
        });



//        int selectedButtonId = toggleGroup.getCheckedButtonId();
//
//        MaterialButton  deliveryButton = (MaterialButton) findViewById(R.id.deliveryButton);
//
//        deliveryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("hhhhhhhhhhhhhhhhhh","de");
//                if(user.getAddress() == null) {
//                    ErrorPopUp.errorMsg(getBaseContext(), "No address found. Please add an address to your account");
//                }else {
//                    ErrorPopUp.errorMsg(getBaseContext(), "Order will be delivered at" + user.getAddress());
//                }
//
//            }
//        });
//
//
//
//        MaterialButton pickupButton = (MaterialButton) findViewById(R.id.deliveryButton);
//
//        pickupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String RestaurantAddress = "";
//
//                for(int i=0; i<user.getFoodCart().size();i++) {
//                    FoodItem food = user.getFoodCart().get(i);
//                    int id = food.getRestaurant_id();
//                    RestaurantServices restaurantServices = new RestaurantServices(Services.getRestaurantPersistence());
//                    Restaurant rest = restaurantServices.getRest(id);
//                    RestaurantAddress += rest.getRestaurant_location() + "\n";
//                }
//
//
//                ErrorPopUp.errorMsg(getBaseContext(), "You can pick up your order " + RestaurantAddress );
//
//            }
//        });



        TextView subTotalTextView = findViewById(R.id.SubTotal);
        TextView DeliveryFeeView = findViewById(R.id.Delivery);
        TextView TaxView = findViewById(R.id.Tax);

        double subTotal = 10.50; // filler temporary get from cart table
        double deliveryFee = 3; // filler temporary check if member
        double tax = subTotal*0.07;
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

                Intent intent = new Intent(getBaseContext(), CheckoutActivity.class);
                startActivity(intent); // Start the cart activity class.
            }
        });


//               if (!user.getFoodCart().isEmpty()) { //Check the food list is empty or not.
//                   user.clearFoodCart(); // Empty the food list.
//                    ErrorPopUp.errorMsg(view.getContext(), "Order Placed!"); // Display message that order placed.
//                    updateCartInfo(user.getFoodCart()); // Display the food card.
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                       public void run() {
//                            Intent intent = getIntent();
//                            finish();
//                            startActivity(intent);
//                        }
//                    }, 3000);
//                } else
//                   ErrorPopUp.errorMsg(view.getContext(), "Cart is empty");
//            }
//        });
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