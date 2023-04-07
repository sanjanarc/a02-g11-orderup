package com.example.orderup.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderup.Objects.FoodItem;
import com.example.orderup.R;
import com.example.orderup.logic.UserServices;

import java.util.List;

public class MyCartsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_carts);

        List<FoodItem> foodList = UserServices.getUser().getFoodCart();

        RecyclerView recyclerView= findViewById(R.id.cartViewRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyCartAdapter(foodList));

        Button PlaceOrderButton = findViewById(R.id.PlaceOrder);
        PlaceOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UserServices.getUser().getFoodCart().isEmpty()) {
                    UserServices.getUser().getOrderHistory().add(UserServices.getUser().getFoodCart());
                    UserServices.getUser().clearFoodCart();
                    ErrorPopUp.errorMsg(view.getContext(), "Order Placed!");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    }, 3000);
                }
                else
                    ErrorPopUp.errorMsg(view.getContext(), "Cart is empty");
            }
        });

    }
}