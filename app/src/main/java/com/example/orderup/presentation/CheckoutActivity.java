package com.example.orderup.presentation;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.MyException;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;
import com.example.orderup.logic.UserVerification;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_method);

        UserServices userServices = new UserServices(Services.getUserPersistence());
        User user = userServices.getUser(Services.getCurrentUser());
        UserVerification userVerification = new UserVerification(Services.getUserPersistence());

        //initialize textviews
        TextView subTotalTextView = findViewById(R.id.SubTotal);
        TextView DeliveryFeeView = findViewById(R.id.Delivery);
        TextView TaxView = findViewById(R.id.Tax);
        TextView TotalView = findViewById(R.id.Total);

        //initialize editviews
        EditText fullNameEditText = findViewById(R.id.fullNameEditText);
        EditText cardNumberEditText = findViewById(R.id.cardNumberEditText);
        EditText expirationDateEditText = findViewById(R.id.expirationDateEditText);
        EditText cvvEditText = findViewById(R.id.cvvEditText);

        double subTotal = 0.00;
        double deliveryFee = 0.00;

        // Get subtotal
        for (int i = 0; i < user.getFoodCart().size(); i++) {

            FoodItem food = user.getFoodCart().get(i);
            double price = food.getNumItems() * food.getItemPrice();
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

        // Set the text of the TextViews to the corresponding values
        subTotalTextView.setText(String.format("SubTotal         $%.2f", subTotal));
        DeliveryFeeView.setText(String.format("Delivery Fee   $%.2f", deliveryFee));
        TaxView.setText(String.format("Tax                  $%.2f", tax));
        TotalView.setText(String.format("TOTAL            $%.2f", total));

        // Replace with  pre-existing value
        fullNameEditText.setText(user.getFirstName() + " " + user.getLastName());

        //Check if user already has credit card in system
        if (("" != user.getCreditCard() && null != user.getCreditCard())) {

            cardNumberEditText.setText(user.getCreditCard());
            expirationDateEditText.setText(user.getExpiry());
            cvvEditText.setText(user.getCvc());

        }

        // Place Order Button event listener.
        Button placeOrder = (Button) findViewById(R.id.placeOrder);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get input data from user.
                String fullName = fullNameEditText.getText().toString();
                String cardNum = cardNumberEditText.getText().toString();
                String cardExpiry = expirationDateEditText.getText().toString();
                String cardCvc = cvvEditText.getText().toString();

                try {

                    // Verify the credit card info and store the data if no error.
                    userVerification.paymentVerification(fullName, cardNum, cardCvc, cardExpiry);
                    user.clearFoodCart();

                    // Display message that order placed.
                    ErrorPopUp.errorMsg(view.getContext(), "Order Placed!");

                    // Finish the activity after 3 seconds.
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 4000); // Delay for 4 seconds.

                } catch (Exception e) {

                    String msg;

                    if (e instanceof MyException.EXCEPTION_ILLEGAL_FORMAT) {

                        msg = "Error: Incorrect Card Number Format.";

                    } else if (e instanceof MyException.EXCEPTION_TYPE_MISMATCH) {

                        msg = "Error: Card is not Visa, American Express or Mastercard.";

                    } else if (e instanceof MyException.EXCEPTION_CVC_LENGTH_DOES_NOT_MATCH) {

                        msg = "Error: Incorrect CVC length.";

                    } else if (e instanceof MyException.EXCEPTION_ILLEGAL_DATE_FORMAT) {

                        msg = "Error: Incorrect Expiry date length.";

                    } else if (e instanceof MyException.EXCEPTION_ILLEGAL_DATE_FORMAT2) {

                        msg = "Error: Incorrect Expiry date.";

                    } else if (e instanceof MyException.EXCEPTION_EMPTY_INPUT) {

                        msg = "Missing Field: Please check you have entered all fields.";

                    } else if (e instanceof MyException.EXCEPTION_ILLEGAL_FULL_NAME_FORMAT) {

                        msg = "Incorrect Format: Should be FirstName LastName.";

                    } else {

                        msg = e.getMessage();

                    }

                    // Display the error message.
                    ErrorPopUp.errorMsg(CheckoutActivity.this, msg);
                }
            }
        });
    }
}
