package com.example.orderup.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.User;
import com.example.orderup.R;
import com.example.orderup.logic.Services;
import com.example.orderup.logic.UserServices;

import java.util.List;

/**
 * The cart page UI class.
 */
public class MyCartsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_carts);

        UserServices userServices = new UserServices(Services.getUserPersistence(), Services.getCurrentUser());
        User user = userServices.getUser();

        // Display the food list.
        updateCartInfo(user.getFoodCart());

        // Place order button event listener.
        Button PlaceOrderButton = findViewById(R.id.PlaceOrder);
        PlaceOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!user.getFoodCart().isEmpty()) { //Check the food list is empty or not.

                    user.clearFoodCart(); // Empty the food list.
                    ErrorPopUp.errorMsg(view.getContext(), "Order Placed!"); // Display message that order placed.
                    updateCartInfo(user.getFoodCart()); // Display the food card.

//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Intent intent = getIntent();
//                            finish();
//                            startActivity(intent);
//                        }
//                    }, 3000);
                } else
                    ErrorPopUp.errorMsg(view.getContext(), "Cart is empty");
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