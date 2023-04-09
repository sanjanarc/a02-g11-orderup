package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.User;
import com.example.orderup.R;
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


        MaterialButtonToggleGroup toggleGroup = findViewById(R.id.toggleGroup);

        int selectedButtonId = toggleGroup.getCheckedButtonId();

        if (selectedButtonId == R.id.deliveryButton) {
            // Delivery option is selected
        } else if (selectedButtonId == R.id.pickupButton) {
            // Pickup option is selected
        } else {
//            ErrorPopUp.errorMsg(view.getContext(), "Please select Delivery or Pick up");
        }


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