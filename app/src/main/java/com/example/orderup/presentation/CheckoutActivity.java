package com.example.orderup.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_method);

        UserServices userServices = new UserServices(Services.getUserPersistence());
        User user = userServices.getUser(Services.getCurrentUser());


        //initialize textviews
        TextView subTotalTextView = findViewById(R.id.SubTotal);
        TextView DeliveryFeeView = findViewById(R.id.Delivery);
        TextView TaxView = findViewById(R.id.Tax);
        TextView TotalView = findViewById(R.id.Total);


        double subTotal = 10.50; // filler temporary get from cart table
        double deliveryFee = 3;   // filler get membership discount if member
        double tax = subTotal * 0.07;
        double total = subTotal + deliveryFee + tax;


        // Set the text of the TextViews to the corresponding values
        subTotalTextView.setText(String.format("SubTotal       $%.2f", subTotal));
        DeliveryFeeView.setText(String.format("Delivery Fee   $%.2f", deliveryFee));
        TaxView.setText(String.format("Tax                  $%.2f", tax));
        TotalView.setText(String.format("TOTAL           $%.2f", total));

        // Place Order Button event listener.
        Button placeOrder = (Button) findViewById(R.id.placeOrder);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.clearFoodCart();
                ErrorPopUp.errorMsg(view.getContext(), "Order Placed!"); // Display message that order placed.
            }


        });

    }

}
